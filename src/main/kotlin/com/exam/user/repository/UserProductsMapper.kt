package com.exam.user.repository

import com.exam.user.dto.UserProductsDto
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserProductsMapper {
    fun selectUserInvestedProducts(userId: String): ArrayList<UserProductsDto>
}