package com.exam.invest.entity

import com.exam.core.base.BaseEntity
import java.math.BigDecimal
import java.util.*


data class InvestProductHistory (
    var productId: String?,
    var userId: String?,
    var investedAt: Date?,
    var investAmount: BigDecimal?,
    var cancelYn: String?
): BaseEntity() {
    constructor(): this(null, null, null, null, null)
}