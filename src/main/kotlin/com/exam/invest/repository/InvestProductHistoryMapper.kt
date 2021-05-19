package com.exam.invest.repository

import com.exam.invest.dto.InvestProductInfoDto
import com.exam.invest.entity.InvestProductHistory
import org.apache.ibatis.annotations.Mapper
import java.math.BigDecimal

@Mapper
interface InvestProductHistoryMapper {
    fun selectProductInvestedAmount(productId: String): BigDecimal?
    fun selectUserProductInvestedAmount(investProductHistory: InvestProductHistory): BigDecimal?
    fun selectProductInvestedStatus(productId: String): InvestProductInfoDto?
    fun insertUserProductInvestHistory(investProductHistory: InvestProductHistory)
}