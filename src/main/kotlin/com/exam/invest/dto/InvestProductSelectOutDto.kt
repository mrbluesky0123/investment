package com.exam.invest.dto

import com.exam.core.base.BaseOutDto
import com.exam.user.dto.UserProductsOutDto

class InvestProductSelectOutDto(
    val productInfo: ArrayList<InvestProductInfoDto>
): BaseOutDto() {
}