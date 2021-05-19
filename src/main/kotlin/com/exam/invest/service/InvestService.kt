package com.exam.invest.service

import com.exam.core.base.BaseService
import com.exam.core.exception.BusinessException
import com.exam.core.util.DateUtils
import com.exam.invest.dto.InvestProductInDto
import com.exam.invest.dto.InvestProductInfoDto
import com.exam.invest.dto.InvestProductOutDto
import com.exam.invest.dto.InvestProductSelectOutDto
import com.exam.invest.entity.InvestProduct
import com.exam.invest.entity.InvestProductHistory
import com.exam.invest.repository.InvestProductHistoryMapper
import com.exam.invest.repository.InvestProductMapper
import com.exam.user.entity.User
import com.exam.user.entity.UserAccount
import com.exam.user.repository.UserAccountMapper
import com.exam.user.repository.UserMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class InvestService(val investProductMapper: InvestProductMapper, val userMapper: UserMapper,
                    val userAccountMapper: UserAccountMapper,
                    val investProductHistoryMapper: InvestProductHistoryMapper): BaseService() {

    fun getAllAvailableProducts(): InvestProductSelectOutDto {

        var investProductInfoDtoList: ArrayList<InvestProductInfoDto> = ArrayList()
        // 1. 현재 투자 가능한 투자 상품 조회
        var allAvailableProducts = this.investProductMapper.selectAllAvailableProducts()

        // 2. 각 상품의 현재 상태 조회
        for(availableProduct in allAvailableProducts) {
            var productId = availableProduct.productId
            // 상품ID, 현재 모집 금액, 투자자 수
            var investProductInfoDto = this.investProductHistoryMapper.selectProductInvestedStatus(productId)
                ?: InvestProductInfoDto(productId, null, BigDecimal.ZERO, null,
                    null, null, 0, null, null)
            // 그 외
            investProductInfoDto.startedAt = availableProduct.startedAt
            investProductInfoDto.finishedAt = availableProduct.finishedAt
            investProductInfoDto.productName = availableProduct.productName
            investProductInfoDto.maxInvestAmount = availableProduct.maxInvestAmount
            investProductInfoDto.totalInvestAmount = availableProduct.totalInvestAmount
            if(investProductInfoDto.currentInvestAmount!! < availableProduct.totalInvestAmount) {
                investProductInfoDto.currentStatus = "모집중"
            } else {
                investProductInfoDto.currentStatus = "모집완료"
            }
            investProductInfoDtoList.add(investProductInfoDto)
        }
        var investProductSelectOutDto = InvestProductSelectOutDto(investProductInfoDtoList)
        investProductSelectOutDto.responseCode = "00.000"
        investProductSelectOutDto.responseMessage = "정상"

        return investProductSelectOutDto
    }

    @Transactional
    fun investProduct(investProductInDto: InvestProductInDto): InvestProductOutDto {
        // 1. 입력값 business validation
        this.validateInvest(investProductInDto)

        // 2. 투자 실행
        val investOutDto = this.processInvest(investProductInDto)

        return investOutDto
    }

    fun validateInvest(investProductInDto: InvestProductInDto) {

        // 0. 회원 검증
        var user: User? = this.userMapper.selectOneUser(investProductInDto.userId)
            ?: throw BusinessException("02.001", "미존재 회원입니다.")

        // 1. 계좌 검증
        val userAccount: UserAccount? = this.userAccountMapper.selectOneUserAccount(investProductInDto.userId)
            ?: throw BusinessException("02.002", "회원의 계좌가 존재하지 않습니다.")

        // 1.1. 휴면 계좌 여부
        if(userAccount!!.inactiveYn == "Y") {
            throw BusinessException("02.003", "휴면계좌입니다. 휴면을 해제해주세요.")
        }
        // 1.2. 계좌 잔액 검증
        if(userAccount!!.balanceAmount!! < investProductInDto.investAmount) {
            throw BusinessException("02.004", "계좌의 잔액이 부족합니다.")
        }

        // 2. 투자 가능 여부 검증
        // 2.1. 투자 상품 존재 여부
        val investProduct: InvestProduct = this.investProductMapper.selectOneProduct(investProductInDto.productId)
            ?: throw BusinessException("03.001", "투자 상품이 존재하지 않습니다.")

        // 2.1. 가능 일자 확인
        if(!(investProduct.startedAt <= DateUtils.nowDate() && DateUtils.nowDate() <= investProduct.finishedAt)) {
            throw BusinessException("03.002", "투자 가능한 기간이 아닙니다.")
        }
        // 2.2. 투자 완료 여부 확인
        val investedAmount = this.investProductHistoryMapper.selectProductInvestedAmount(investProductInDto.productId)
            ?: BigDecimal.ZERO
        if(investedAmount >= investProduct.totalInvestAmount) {
            throw BusinessException("03.003", "투자금 모집 완료 상품입니다.(SOLD-OUT)")
        }
        // 2.3. 사용자 투자 내역 존재 확인
        var userInvestProductHistory = InvestProductHistory()
        userInvestProductHistory.productId = investProductInDto.productId
        userInvestProductHistory.userId = investProductInDto.userId
        val userInvestedAmount = this.investProductHistoryMapper
            .selectUserProductInvestedAmount(userInvestProductHistory) ?: BigDecimal.ZERO

        if(userInvestedAmount > BigDecimal.ZERO) {
            throw BusinessException("03.004", "투자 내역이 존재합니다. 한 투자 상품에 한 번만 투자할 수 있습니다.")
        }
        // 2.4 투자할 금액이 투자 가능 max 값 초과하는지 확인
        if(investProductInDto.investAmount > investProduct.maxInvestAmount) {
            throw BusinessException("03.005", "본 상품은 최대 ${investProduct.maxInvestAmount}원을 투자할 수 있습니다.")
        }

    }

    fun processInvest(investProductInDto: InvestProductInDto): InvestProductOutDto {

        // 1. 투자 내역 적재
        var investProductHistory = InvestProductHistory()
        investProductHistory.productId = investProductInDto.productId
        investProductHistory.userId = investProductInDto.userId
        investProductHistory.investAmount = investProductInDto.investAmount
        investProductHistory.cancelYn = "N"

        // 2. 사용자 계좌 잔액 차감
        val userAccount: UserAccount? = this.userAccountMapper.selectOneUserAccount(investProductInDto.userId)
            ?: throw BusinessException("02.002", "회원의 계좌가 존재하지 않습니다.")
        val newBalance = userAccount!!.balanceAmount!! - investProductInDto.investAmount
        if(newBalance < BigDecimal.ZERO) {
            throw BusinessException("02.004", "계좌의 잔액이 부족합니다.")
        }
        val newUserAccount = UserAccount()
        newUserAccount.userId = investProductInDto.userId
        newUserAccount.balanceAmount = newBalance
        this.userAccountMapper.updateUserAccountBalance(newUserAccount)

        // 3. 응답 전문 세팅
        val investOutDto = InvestProductOutDto(investProductInDto.productId, newBalance)
        investOutDto.responseCode = "00.000"
        investOutDto.responseMessage = "정상"

        return investOutDto

    }

}