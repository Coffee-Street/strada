package com.wnsgml972.strada.handler

import com.wnsgml972.strada.exception.BusinessException
import com.wnsgml972.strada.exception.InvalidArgumentException
import com.wnsgml972.strada.exception.NotFoundException
import com.wnsgml972.strada.exception.UnAuthorizedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.zalando.problem.spring.web.advice.AdviceTrait
import java.time.LocalDateTime

interface StradaExceptionHandler : AdviceTrait {

    @ExceptionHandler(UnAuthorizedException::class)
    fun handleUnAuthorizedException(e: UnAuthorizedException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(InvalidArgumentException::class)
    fun handleBadRequest(e: InvalidArgumentException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException) : ResponseEntity<ErrorResponse>{
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    data class ErrorResponse(
        val code: String?,
        val message: String?,
        val timestamp: LocalDateTime = LocalDateTime.now()
    )
}
