package com.exam.invest.controller

import com.exam.invest.dto.InvestProductInDto
import com.exam.invest.dto.InvestProductOutDto
import com.exam.invest.dto.InvestProductSelectOutDto
import com.exam.invest.service.InvestService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/invest")
class InvestController @Autowired constructor (val investService: InvestService) {

    @GetMapping("/allproducts")
    @ApiOperation(value="투자 가능한 투자 상품 조회")
    fun getAllAvailableProducts(): InvestProductSelectOutDto = investService.getAllAvailableProducts()

    @PostMapping("/product")
    @ApiOperation(value="투자 상품에 투자 실행")
    fun investProducts(@RequestBody @Valid investProductInDto: InvestProductInDto): InvestProductOutDto =
        investService.investProduct(investProductInDto)

}