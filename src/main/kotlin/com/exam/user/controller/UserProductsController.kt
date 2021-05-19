package com.exam.user.controller

import com.exam.core.base.BaseController
import com.exam.user.dto.UserProductsInDto
import com.exam.user.dto.UserProductsSelectOutDto
import com.exam.user.service.UserProductsService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserProductsController(val userProductsService: UserProductsService): BaseController() {

    @GetMapping("/userproducts")
    @ApiOperation(value="각 회원이 투자한 투자 상품 조회")
    fun getUserInvestedProducts(userProductsInDto: UserProductsInDto): UserProductsSelectOutDto =
        userProductsService.getUserInvestedProducts(userProductsInDto)

}