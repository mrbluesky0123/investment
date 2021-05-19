package com.exam.user.repository

import com.exam.user.dto.UserProductsDto
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@MybatisTest
class UserProductsRepositoryTest {

    @Autowired
    lateinit var userProductsMapper: UserProductsMapper

    @Test
    fun 회원_투자상품_조회_정상() {
        val userProductsList: ArrayList<UserProductsDto> = this.userProductsMapper
            .selectUserInvestedProducts("2021050004")
        assertThat(userProductsList.size, `is`(2))
        assertThat(userProductsList[0].productId, `is`("CRD00001"))
    }

    @Test
    fun 회원_투자상품_조회_투자내역미존재() {
        val userProductsList: ArrayList<UserProductsDto> = this.userProductsMapper
            .selectUserInvestedProducts("20210500041")
        assertThat(userProductsList.size, `is`(0))
    }
}