package com.exam.invest.entity

import com.exam.core.base.BaseEntity
import java.math.BigDecimal
import java.util.*

data class InvestProduct(
    val productId: String,
    val productCategory: String,
    val productName: String,
    val startedAt: Date,
    val finishedAt: Date,
    val totalInvestAmount: BigDecimal,
    val maxInvestAmount: BigDecimal
): BaseEntity()