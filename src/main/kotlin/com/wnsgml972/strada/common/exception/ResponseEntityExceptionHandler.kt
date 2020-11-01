package com.wnsgml972.strada.common.exception

import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class ResponseEntityExceptionHandler {
    companion object : KLogging()

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException::class)
    fun handleAccessDeniedException(e: org.springframework.security.access.AccessDeniedException): ResponseEntity<ErrorResponse> {
        logger.debug("handleAccessDeniedException : ", e)
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        logger.debug("handleNotFoundException : ", e)
        val response = ErrorResponse(null, e.message)
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        logger.debug("handleBusinessException : ", e)
        val response = ErrorResponse(e.code, e.message)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleUncaughtException(e: Exception): ResponseEntity<ErrorResponse> {
        logger.error("handleUncaughtException : ", e)
        val response = ErrorResponse(null, "Internal Server Error")
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    data class ErrorResponse(
        val code: String?,
        val message: String?,
        val timestamp: LocalDateTime = LocalDateTime.now()
    )
}