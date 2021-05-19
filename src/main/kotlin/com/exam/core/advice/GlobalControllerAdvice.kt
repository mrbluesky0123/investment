package com.exam.core.advice

import ch.qos.logback.classic.Logger
import com.exam.core.base.BaseInDto
import com.exam.core.base.BaseOutDto
import com.exam.core.exception.BusinessException
import com.exam.core.exception.ValidationException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


@Aspect
@RestControllerAdvice
class GlobalControllerAdvice {
    companion object {
        val log: Logger = LoggerFactory.getLogger(GlobalControllerAdvice::class.java) as Logger
    }

    @Pointcut("execution(* com.exam..*.controller..*.*(*))")
    fun controllers() {}

    @Around("controllers()")
    fun aroundController(pjp: ProceedingJoinPoint): Any? {
        var result: Any? = null
        // 회원 ID 주입
        val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request
        val baseInDto: BaseInDto = (pjp.args)[0] as BaseInDto
        baseInDto.userId = req.getHeader("X-USER-ID")
            ?: throw ValidationException("01.001", "회원ID는 필수값입니다.")

        result = pjp.proceed()

        return result

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(businessException: BusinessException): BaseOutDto {
        val outDto = BaseOutDto()
        outDto.responseCode = businessException.responseCode
        outDto.responseMessage = businessException.responseMessage
        return outDto
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(methodArgumentNotValidException: MethodArgumentNotValidException):
            BaseOutDto {
        val outDto = BaseOutDto()
        outDto.responseCode = "01.001"  // 필수값 누락
        outDto.responseMessage = methodArgumentNotValidException.bindingResult.fieldError!!.defaultMessage!!
        return outDto
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(validationException: ValidationException): BaseOutDto {
        val outDto = BaseOutDto()
        outDto.responseCode = "01.001"  // 필수값 누락
        outDto.responseMessage = validationException.responseMessage
        return outDto
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleAnotherException(exception: Exception): BaseOutDto {
        val outDto = BaseOutDto()
        outDto.responseCode = "99.999"
        outDto.responseMessage = "시스템실 연락 요망"
        return outDto
    }

}