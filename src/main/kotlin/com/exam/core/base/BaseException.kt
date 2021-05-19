package com.exam.core.base

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
open class BaseException(
    open val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    open val responseCode: String,
    open val responseMessage: String,
    e: Throwable? = null,
) : RuntimeException(responseCode, e) {
    constructor(e: Throwable, responseCode: String, responseMessage: String) :
            this(httpStatus = HttpStatus.INTERNAL_SERVER_ERROR, responseCode = responseCode,
                responseMessage = responseMessage, e = e)
}