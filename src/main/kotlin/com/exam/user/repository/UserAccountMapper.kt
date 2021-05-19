package com.exam.user.repository

import com.exam.user.entity.UserAccount
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserAccountMapper {
    fun selectOneUserAccount(userId: String): UserAccount?
    fun updateUserAccountBalance(userAccount: UserAccount)
}