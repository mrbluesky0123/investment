package com.exam.user.dto

import java.math.BigDecimal
import java.util.*

class UserProductsOutDto(
    var productId: String?,
    var productName: String?,
    var investedAt: Date?,
    var totalInvestAmount: BigDecimal?,
    var userInvestAmount: BigDecimal?,
    var currentInvestAmount: BigDecimal?,
    var investorNumber: Int?
){
    constructor(): this(null, null, null,
        null,null, null, null, )
}