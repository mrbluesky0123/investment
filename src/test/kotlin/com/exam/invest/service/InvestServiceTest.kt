package com.exam.invest.service

import com.exam.core.exception.BusinessException
import com.exam.invest.dto.InvestProductInDto
import com.exam.invest.dto.InvestProductInfoDto
import com.exam.invest.dto.InvestProductSelectOutDto
import com.exam.invest.repository.InvestProductHistoryMapper
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@SpringBootTest
class InvestServiceTest {

    @Autowired
    lateinit var investService: InvestService

    @Autowired
    lateinit var investProductHistoryMapper: InvestProductHistoryMapper

    @Test
    fun 투자_가능_상품_조회_정상() {
        var investProductSelectOutDto: InvestProductSelectOutDto = this.investService.getAllAvailableProducts()
        assertThat(investProductSelectOutDto.productInfo[0].investorNumber, `is`(4))
        assertThat(investProductSelectOutDto.productInfo[1].currentStatus, `is`("모집중"))
        assertThat(investProductSelectOutDto.productInfo[2].productName, `is`("RealEstateFunding2"))
        assertThat(investProductSelectOutDto.productInfo[3].currentInvestAmount, `is`(BigDecimal.ZERO))
    }

    @Test
    fun 입력값_유효성_체크_정상(){
        var investInDto = InvestProductInDto("RES00001", BigDecimal.valueOf(10000))
        investInDto.userId = "2021050004"
        investService.validateInvest(investInDto)
    }

    @Test()
    fun 입력값_유효성_체크_미존재회원() {
        var investInDto = InvestProductInDto("CRD00001", BigDecimal.valueOf(10000))
        investInDto.userId = "20210500014"
        val exception = assertThrows(BusinessException::class.java) { investService.validateInvest(investInDto) }
        assertThat(exception.responseCode, `is`("02.001"))
        assertThat(exception.responseMessage, `is`("미존재 회원입니다."))
    }

    @Test
    fun 입력값_유효성_체크_미존재계좌() {
        var investInDto = InvestProductInDto("CRD00001", BigDecimal.valueOf(10000))
        investInDto.userId = "2021050006"
        val exception = assertThrows(BusinessException::class.java) { investService.validateInvest(investInDto) }
        assertThat(exception.responseCode, `is`("02.002"))
        assertThat(exception.responseMessage, `is`("회원의 계좌가 존재하지 않습니다."))
    }

    @Test
    fun 입력값_유효성_체크_휴면계좌() {
        var investInDto = InvestProductInDto("CRD00001", BigDecimal.valueOf(10000))
        investInDto.userId = "2021050005"
        val exception = assertThrows(BusinessException::class.java) { investService.validateInvest(investInDto) }
        assertThat(exception.responseCode, `is`("02.003"))
        assertThat(exception.responseMessage, `is`("휴면계좌입니다. 휴면을 해제해주세요."))
    }

    @Test
    fun 입력값_유효성_체크_잔액부족() {
        var investInDto = InvestProductInDto("CRD00001", BigDecimal.valueOf(9999999999))
        investInDto.userId = "2021050004"
        val exception = assertThrows(BusinessException::class.java) { investService.validateInvest(investInDto) }
        assertThat(exception.responseCode, `is`("02.004"))
        assertThat(exception.responseMessage, `is`("계좌의 잔액이 부족합니다."))
    }

    @Test
    fun 입력값_유효성_체크_투자가능기간이아님() {
        var investInDto = InvestProductInDto("CRD00002", BigDecimal.valueOf(90000))
        investInDto.userId = "2021050004"
        val exception = assertThrows(BusinessException::class.java) { investService.validateInvest(investInDto) }
        assertThat(exception.responseCode, `is`("03.002"))
        assertThat(exception.responseMessage, `is`("투자 가능한 기간이 아닙니다."))
    }

    @Test
    fun 입력값_유효성_체크_투자금모집완료() {
        var investInDto = InvestProductInDto("RES00002", BigDecimal.valueOf(90000))
        investInDto.userId = "2021050002"
        val exception = assertThrows(BusinessException::class.java) { investService.validateInvest(investInDto) }
        assertThat(exception.responseCode, `is`("03.003"))
        assertThat(exception.responseMessage, `is`("투자금 모집 완료 상품입니다.(SOLD-OUT)"))
    }

    @Test
    fun 입력값_유효성_체크_투자내역기존재() {
        var investInDto = InvestProductInDto("CRD00001", BigDecimal.valueOf(90000))
        investInDto.userId = "2021050002"
        val exception = assertThrows(BusinessException::class.java) { investService.validateInvest(investInDto) }
        assertThat(exception.responseCode, `is`("03.004"))
        assertThat(exception.responseMessage, `is`("투자 내역이 존재합니다. 한 투자 상품에 한 번만 투자할 수 있습니다."))
    }

    @Test
    fun 입력값_유효성_체크_최대투자금액초과() {
        var investInDto = InvestProductInDto("RES00001", BigDecimal.valueOf(1000000))
        investInDto.userId = "2021050004"
        val exception = assertThrows(BusinessException::class.java) { investService.validateInvest(investInDto) }
        assertThat(exception.responseCode, `is`("03.005"))
        assertThat(exception.responseMessage, `is`("본 상품은 최대 700000원을 투자할 수 있습니다."))
    }

    @Test
    @Transactional
    fun 투자_실행_정상() {
        var investInDto = InvestProductInDto("RES00001", BigDecimal.valueOf(10000))
        investInDto.userId = "2021050004"
        val investOutDto = investService.processInvest(investInDto)
        assertThat(investOutDto.responseCode, `is`("00.000"))
    }

    @Test
    fun 투자_실행_계좌미존재() {
        var investInDto = InvestProductInDto("RES00001", BigDecimal.valueOf(1000000))
        investInDto.userId = "2021050006"
        val exception = assertThrows(BusinessException::class.java) { investService.processInvest(investInDto) }
        assertThat(exception.responseCode, `is`("02.002"))
        assertThat(exception.responseMessage, `is`("회원의 계좌가 존재하지 않습니다."))
    }

    @Test
    fun 투자_실행_잔액부족() {
        var investInDto = InvestProductInDto("RES00001", BigDecimal.valueOf(99999999))
        investInDto.userId = "2021050001"
        val exception = assertThrows(BusinessException::class.java) { investService.processInvest(investInDto) }
        assertThat(exception.responseCode, `is`("02.004"))
        assertThat(exception.responseMessage, `is`("계좌의 잔액이 부족합니다."))
    }

}