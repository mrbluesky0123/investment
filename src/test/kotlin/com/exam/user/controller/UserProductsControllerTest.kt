package com.exam.user.controller

import com.exam.core.dto.ErrorOutDto
import com.exam.user.dto.UserProductsSelectOutDto
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMybatis
class UserProductsControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun 회원_투자_상품_조회_컨트롤러_200() {
        var httpHeaders = HttpHeaders()
        httpHeaders.set("X-USER-ID", "2021050001")
        var result: ResponseEntity<UserProductsSelectOutDto> = testRestTemplate
            .exchange("/user/userproducts", HttpMethod.GET,
                HttpEntity<Any?>(httpHeaders), UserProductsSelectOutDto::class.java)
        assertThat(result.statusCode, `is`(HttpStatus.OK))
    }

    @Test
    fun 회원_투자_상품_조회_컨트롤러_4XX() {
        var httpHeaders = HttpHeaders()
        var result: ResponseEntity<ErrorOutDto> = testRestTemplate
            .exchange("/user/userproducts", HttpMethod.GET,
                HttpEntity<Any?>(httpHeaders), ErrorOutDto::class.java)
        assertThat(result.statusCode, `is`(HttpStatus.BAD_REQUEST))
    }

    @Test
    fun 회원_투자_상품_조회_컨트롤러_5XX() {
        var httpHeaders = HttpHeaders()
        httpHeaders.set("X-USER-ID", "2021050006")
        var result: ResponseEntity<ErrorOutDto> = testRestTemplate
            .exchange("/user/userproducts", HttpMethod.GET,
                HttpEntity<Any?>(httpHeaders), ErrorOutDto::class.java)
        assertThat(result.statusCode, `is`(HttpStatus.INTERNAL_SERVER_ERROR))
    }

}
