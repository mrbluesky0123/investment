package com.exam.user.entity

import com.exam.core.base.BaseEntity

data class User(
    val userId: String,
    val userName: String?,
    val userEmail: String?

): BaseEntity()