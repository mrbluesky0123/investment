package com.exam.invest.dto

import com.exam.core.base.BaseInDto
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class InvestProductInDto(
    @field:NotNull(message = "투자상품ID는 필수입력값입니다.")
    @field:NotEmpty(message = "투자상품ID는 필수입력값입니다.")
    val productId: String,

    @field:NotNull(message = "투자금액은 필수입력값입니다.")
    @field:DecimalMin(value = "1", message = "투자금액은 0 또는 음수가 될 수 없습니다.")
    val investAmount: BigDecimal
): BaseInDto()