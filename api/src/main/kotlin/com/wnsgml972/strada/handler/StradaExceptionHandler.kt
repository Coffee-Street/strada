package com.wnsgml972.strada.handler

import com.wnsgml972.strada.exception.BadRequestException
import com.wnsgml972.strada.exception.NotFoundException
import com.wnsgml972.strada.exception.IllegalStateException
import com.wnsgml972.strada.exception.UnAuthorizedException
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.zalando.problem.spring.web.advice.AdviceTrait
import java.time.LocalDateTime

interface StradaExceptionHandler : AdviceTrait {

    @ExceptionHandler(UnAuthorizedException::class)
    fun handleUnAuthorizedException(e: UnAuthorizedException): ResponseEntity<ErrorResponse> {
        logger.debug("handleUnAuthorizedException : ", e)
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(e: BadRequestException): ResponseEntity<ErrorResponse> {
        logger.debug("handleBadRequestException : ", e)
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        logger.debug("handleNotFoundException : ", e)
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ErrorResponse> {
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
