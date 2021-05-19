package com.exam.invest.repository

import com.exam.invest.entity.InvestProduct
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@MybatisTest
class InvestProductRepositoryTest {

    @Autowired
    lateinit var investProductMapper: InvestProductMapper

    @Test
    fun 투자_가능_상품_조회_정상() {
        var investProducts: List<InvestProduct> = investProductMapper.selectAllAvailableProducts()
        assertEquals("CRD00001", investProducts[0].productId)
        assertEquals("RES00001", investProducts[1].productId)
        assertEquals("RES00002", investProducts[2].productId)
    }

    @Test
    fun 단일_투자_가능_상품_조회_정상() {
        var investProduct: InvestProduct? = investProductMapper.selectOneProduct("CRD00001")
        assertThat(investProduct?.productId, `is`("CRD00001"))
    }

    @Test
    fun 단일_투자_가능_상품_조회_미존재() {
        var investProduct: InvestProduct? = investProductMapper.selectOneProduct("CRD000012")
        assertThat(investProduct, `is`(nullValue()))
    }
}