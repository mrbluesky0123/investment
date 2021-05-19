package com.exam.core.exception

import com.exam.core.base.BaseException
import org.springframework.http.HttpStatus

class ValidationException (
    override val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
    override val responseCode: String,
    override val responseMessage: String
) : BaseException(httpStatus, responseCode, responseMessage) {
    constructor(responseCode: String, responseMessage: String) :
            this(HttpStatus.BAD_REQUEST, responseCode, responseMessage)
}
