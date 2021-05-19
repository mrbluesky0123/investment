package com.exam.user.dto

import com.exam.core.base.BaseOutDto

class UserProductsSelectOutDto(
    var userInvestedProducts: ArrayList<UserProductsOutDto>
): BaseOutDto()