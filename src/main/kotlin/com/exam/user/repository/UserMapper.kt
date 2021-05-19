package com.exam.user.repository

import com.exam.user.entity.User
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserMapper {
    fun selectOneUser(userId: String): User?

}