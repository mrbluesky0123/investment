package com.exam.user.service

import com.exam.core.base.BaseService
import com.exam.core.exception.BusinessException
import com.exam.invest.dto.InvestProductInfoDto
import com.exam.invest.entity.InvestProduct
import com.exam.invest.repository.InvestProductHistoryMapper
import com.exam.invest.repository.InvestProductMapper
import com.exam.user.dto.UserProductsDto
import com.exam.user.dto.UserProductsInDto
import com.exam.user.dto.UserProductsOutDto
import com.exam.user.dto.UserProductsSelectOutDto
import com.exam.user.entity.User
import com.exam.user.repository.UserMapper
import com.exam.user.repository.UserProductsMapper
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class UserProductsService(val investProductMapper: InvestProductMapper, val userMapper: UserMapper,
                          val investProductHistoryMapper: InvestProductHistoryMapper,
                          val userProductsMapper: UserProductsMapper): BaseService() {

    fun getUserInvestedProducts(userProductsInDto: UserProductsInDto): UserProductsSelectOutDto {

        var userProductsOutDtoList: ArrayList<UserProductsOutDto> = ArrayList()

        // 1. 회원 존재 여부 체크
        var user: User? = this.userMapper.selectOneUser(userProductsInDto.userId)
            ?: throw BusinessException("02.001", "미존재 회원입니다.")

        // 2. 투자한 상품 조회
        var userProductsDtoList: ArrayList<UserProductsDto> = this.userProductsMapper
            .selectUserInvestedProducts(userProductsInDto.userId)
        if(userProductsDtoList.size == 0) {
            throw BusinessException("02.004", "투자상품 내역이 존재하지 않습니다.")
        }

        // 3. 투자한 상품의 현재 상태 조회
        for(userInvestedProduct in userProductsDtoList) {
            var productId = userInvestedProduct.productId
            var investProduct: InvestProduct? = this.investProductMapper.selectOneProduct(productId)
                ?: throw BusinessException("03.001", "투자 상품이 존재하지 않습니다.")
            var investProductInfoDto: InvestProductInfoDto? =
                this.investProductHistoryMapper.selectProductInvestedStatus(productId)
                    ?: InvestProductInfoDto(productId, null, BigDecimal.ZERO, null,
                        null, null, 0, null, null)
            var userProductsOutDto: UserProductsOutDto = UserProductsOutDto()
            userProductsOutDto.productId = productId
            userProductsOutDto.productName = investProduct!!.productName
            userProductsOutDto.investedAt = userInvestedProduct.investedAt
            userProductsOutDto.userInvestAmount = userInvestedProduct.investAmount
            userProductsOutDto.totalInvestAmount = investProduct!!.totalInvestAmount
            userProductsOutDto.investorNumber = investProductInfoDto!!.investorNumber
            userProductsOutDto.currentInvestAmount = investProductInfoDto!!.currentInvestAmount

            userProductsOutDtoList.add(userProductsOutDto)

        }
        val userProductSelectOutDto = UserProductsSelectOutDto(userProductsOutDtoList)
        userProductSelectOutDto.responseCode = "00.000"
        userProductSelectOutDto.responseMessage = "정상"

        return userProductSelectOutDto
    }

}