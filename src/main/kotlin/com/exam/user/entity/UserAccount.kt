package com.exam.user.entity

import com.exam.core.base.BaseEntity
import java.math.BigDecimal

data class UserAccount(
    var accountId: String?,
    var userId: String?,
    var balanceAmount: BigDecimal?,
    var inactiveYn: String?
): BaseEntity() {
    constructor() : this(null, null, null, null)
}