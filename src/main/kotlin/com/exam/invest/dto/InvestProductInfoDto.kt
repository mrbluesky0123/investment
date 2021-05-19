package com.exam.invest.dto

import java.math.BigDecimal
import java.util.*

class InvestProductInfoDto(
    var productId: String?,
    var productName: String?,
    var currentInvestAmount: BigDecimal?,
    var totalInvestAmount: BigDecimal?,
    var maxInvestAmount: BigDecimal?,
    var currentStatus: String?,
    var investorNumber: Int?,
    var startedAt: Date?,
    var finishedAt: Date?
) {
    constructor(): this(null, null, null, null,
        null, null, null, null , null)
}