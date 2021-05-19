package com.exam.core.base

import java.util.*

open class BaseEntity: BaseObject() {
    var createdAt: Date? = null
    var createdUserId: String? = null
    var updatedAt: Date? = null
    var updatedUserId: String? = null
}