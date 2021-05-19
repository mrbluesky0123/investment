package com.exam.invest.dto

import com.exam.core.base.BaseOutDto
import java.math.BigDecimal

class InvestProductOutDto(
    var productId: String?,
    var balance: BigDecimal?
): BaseOutDto()