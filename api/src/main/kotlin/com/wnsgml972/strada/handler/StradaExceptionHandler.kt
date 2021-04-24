package com.wnsgml972.strada.handler

import com.wnsgml972.strada.exception.BadRequestException
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

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(e: BadRequestException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleUnKnownException(e: Exception): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    data class ErrorResponse(
        val code: String?,
        val message: String?,
        val timestamp: String = LocalDateTime.now().toString()
    )
}
