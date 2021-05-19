package com.exam.user.service

import com.exam.user.dto.UserProductsInDto
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class UserProductsServiceTest {
    @Autowired
    lateinit var userProductsService: UserProductsService

    @Test
    fun 회원_투자_상품_조회_정상() {
        var userProductsInDto = UserProductsInDto()
        userProductsInDto.userId = "2021050004"
        var investProductSelectOutDto = this.userProductsService.getUserInvestedProducts(userProductsInDto)
        assertThat(investProductSelectOutDto.userInvestedProducts.size, `is`(2))
        assertThat(investProductSelectOutDto.userInvestedProducts[0].productId, `is`("CRD00001"))
    }
}