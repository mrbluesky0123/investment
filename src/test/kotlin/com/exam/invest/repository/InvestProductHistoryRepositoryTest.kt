package com.exam.invest.repository

import com.exam.invest.entity.InvestProductHistory
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@MybatisTest
class InvestProductHistoryRepositoryTest {
    @Autowired
    lateinit var investProductHistoryMapper: InvestProductHistoryMapper

    @Test
    fun 투자_상품_투자_금액_내역_조회() {
        val investedAmount = this.investProductHistoryMapper.selectProductInvestedAmount("CRD00001")
        assertThat(investedAmount, `is`(BigDecimal.valueOf(150000)))
    }

    @Test
    fun 투자_상품_투자_상태_조회() {
        val investedStatus = this.investProductHistoryMapper.selectProductInvestedStatus("CRD00001")
        assertThat(investedStatus!!.currentInvestAmount, `is`(BigDecimal.valueOf(150000)))
        assertThat(investedStatus!!.investorNumber, `is`(4))
    }

    @Test
    fun 사용자_투자_상품_투자_금액_내역_조회() {
        var investProductHistory = InvestProductHistory()
        investProductHistory.productId = "CRD00001"
        investProductHistory.userId = "2021050004"
        val investedAmount = this.investProductHistoryMapper.selectUserProductInvestedAmount(investProductHistory)
        assertThat(investedAmount, `is`(BigDecimal.valueOf(30000)))
    }

    @Test
    fun 사용자_투자_상품_투자_금액_내역_조회_투자내역미존재() {
        var investProductHistory = InvestProductHistory()
        investProductHistory.productId = "RES00001"
        investProductHistory.userId = "2021050004"
        val investedAmount = this.investProductHistoryMapper.selectUserProductInvestedAmount(investProductHistory)
        assertThat(investedAmount, `is`(nullValue()))
    }

    @Test
    @Transactional
    fun 사용자_투자_내역_적재() {
        var investProductHistory = InvestProductHistory()
        investProductHistory.productId = "RES00002"
        investProductHistory.userId = "2021050001"
        investProductHistory.investAmount = BigDecimal.valueOf(3000)
        investProductHistory.cancelYn = "N"
        this.investProductHistoryMapper.insertUserProductInvestHistory(investProductHistory)
        val investedAmount = this.investProductHistoryMapper.selectUserProductInvestedAmount(investProductHistory)
        assertThat(investedAmount, `is`(BigDecimal.valueOf(3000)))
    }

}