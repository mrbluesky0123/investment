package com.exam.core.exception

import com.exam.core.base.BaseException
import org.springframework.http.HttpStatus

class BusinessException (
    override val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    override val responseCode: String,
    override val responseMessage: String
) : BaseException(httpStatus, responseCode, responseMessage) {
    constructor(responseCode: String, responseMessage: String) :
            this(HttpStatus.INTERNAL_SERVER_ERROR, responseCode, responseMessage)
}
