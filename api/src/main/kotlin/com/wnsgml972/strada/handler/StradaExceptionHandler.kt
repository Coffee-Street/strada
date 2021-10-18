package com.wnsgml972.strada.handler

import com.wnsgml972.strada.exception.StradaBadRequestException
import com.wnsgml972.strada.exception.StradaNotFoundException
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaUnAuthorizedException
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.zalando.problem.spring.web.advice.AdviceTrait
import java.time.LocalDateTime

interface StradaExceptionHandler : AdviceTrait {

    @ExceptionHandler(StradaUnAuthorizedException::class)
    fun handleUnAuthorizedException(e: StradaUnAuthorizedException): ResponseEntity<ErrorResponse> {
        logger.debug("handleUnAuthorizedException : ", e)
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(StradaBadRequestException::class)
    fun handleBadRequestException(e: StradaBadRequestException): ResponseEntity<ErrorResponse> {
        logger.debug("handleBadRequestException : ", e)
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(StradaNotFoundException::class)
    fun handleNotFoundException(e: StradaNotFoundException): ResponseEntity<ErrorResponse> {
        logger.debug("handleNotFoundException : ", e)
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(StradaIllegalStateException::class)
    fun handleIllegalStateException(e: StradaIllegalStateException): ResponseEntity<ErrorResponse> {
        logger.debug("handleIllegalStateException : ", e)
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(Exception::class)
    fun handleUnKnownException(e: Exception): ResponseEntity<ErrorResponse> {
        logger.debug("handleUnKnownException : ", e)
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    data class ErrorResponse(
        val code: String?,
        val message: String?,
        val timestamp: String = LocalDateTime.now().toString()
    )

    companion object : KLogging()
}
