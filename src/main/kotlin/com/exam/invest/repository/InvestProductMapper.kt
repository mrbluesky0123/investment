package com.exam.invest.repository

import com.exam.invest.entity.InvestProduct
import org.apache.ibatis.annotations.Mapper

@Mapper
interface InvestProductMapper {

    fun selectAllAvailableProducts(): List<InvestProduct>
    fun selectOneProduct(productId: String): InvestProduct?

}