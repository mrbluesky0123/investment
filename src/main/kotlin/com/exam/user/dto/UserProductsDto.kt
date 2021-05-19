package com.exam.user.dto

import java.math.BigDecimal
import java.util.*

class UserProductsDto(
    val productId: String,
    val investedAt: Date,
    val investAmount: BigDecimal
) {
}