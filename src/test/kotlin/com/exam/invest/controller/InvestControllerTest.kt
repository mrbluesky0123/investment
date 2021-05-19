package com.exam.invest.controller

import com.exam.invest.dto.InvestProductInDto
import com.exam.invest.dto.InvestProductOutDto
import com.exam.invest.dto.InvestProductSelectOutDto
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMybatis
class InvestControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun 투자_가능_상품_조회_컨트롤러_정상() {
//        var httpHeaders = HttpHeaders()
//        httpHeaders.set("X-USER-ID", "2021050001")
        var result: ResponseEntity<InvestProductSelectOutDto> = testRestTemplate
            .getForEntity("/invest/allproducts", InvestProductSelectOutDto::class.java)
        assertThat(result.statusCode, `is`(HttpStatus.valueOf(200)))
    }

    @Test
    fun 투자_실행_컨트롤러_200() {
        var httpHeaders = HttpHeaders()
        httpHeaders.set("X-USER-ID", "2021050001")

        var investProductInDto = InvestProductInDto("CRD00001", BigDecimal.ONE)
        val httpEntity = HttpEntity<InvestProductInDto>(investProductInDto, httpHeaders)
        val result: ResponseEntity<InvestProductOutDto> =
            this.testRestTemplate.postForEntity("/invest/product", httpEntity, InvestProductOutDto::class.java)
        assertThat(result.statusCode, `is`(HttpStatus.valueOf(200)))
    }

    @Test
    fun 투자_실행_컨트롤러_4XX() {
        var httpHeaders = HttpHeaders()
        httpHeaders.set("X-USER-ID", "2021050001")

        var investProductInDto = InvestProductInDto("", BigDecimal.ONE)
        val httpEntity = HttpEntity<InvestProductInDto>(investProductInDto, httpHeaders)
        val result: ResponseEntity<InvestProductOutDto> =
            this.testRestTemplate.postForEntity("/invest/product", httpEntity, InvestProductOutDto::class.java)
        assertThat(result.statusCode, `is`(HttpStatus.BAD_REQUEST))

        var investProductInDto2 = InvestProductInDto("CRD00001", BigDecimal.valueOf(-1))
        val httpEntity2 = HttpEntity<InvestProductInDto>(investProductInDto2, httpHeaders)
        val result2: ResponseEntity<InvestProductOutDto> =
            this.testRestTemplate.postForEntity("/invest/product", httpEntity2, InvestProductOutDto::class.java)
        assertThat(result2.statusCode, `is`(HttpStatus.BAD_REQUEST))
    }

    @Test
    fun 투자_실행_컨트롤러_5XX() {
        var httpHeaders = HttpHeaders()
        httpHeaders.set("X-USER-ID", "2021050006")

        var investProductInDto = InvestProductInDto("CRD00001", BigDecimal.ONE)
        val httpEntity = HttpEntity<InvestProductInDto>(investProductInDto, httpHeaders)
        val result: ResponseEntity<InvestProductOutDto> =
            this.testRestTemplate.postForEntity("/invest/product", httpEntity, InvestProductOutDto::class.java)
        assertThat(result.statusCode, `is`(HttpStatus.INTERNAL_SERVER_ERROR))

        httpHeaders.set("X-USER-ID", "2021050003")
        var investProductInDto2 = InvestProductInDto("CRD00001", BigDecimal.ONE)
        val httpEntity2 = HttpEntity<InvestProductInDto>(investProductInDto2, httpHeaders)
        val result2: ResponseEntity<InvestProductOutDto> =
            this.testRestTemplate.postForEntity("/invest/product", httpEntity2, InvestProductOutDto::class.java)
        assertThat(result2.statusCode, `is`(HttpStatus.INTERNAL_SERVER_ERROR))

    }


}