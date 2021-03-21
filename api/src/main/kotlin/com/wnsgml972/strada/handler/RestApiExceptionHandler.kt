package com.wnsgml972.strada.handler

import com.wnsgml972.strada.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.zalando.problem.spring.web.advice.ProblemHandling

/**
 * Problem 라이브러리를 이용하여 REST API 에서 발생한 예외 정보를 Client에게 전송해주는 Handler 입니다.
 *
 * 참고: https://github.com/zalando/problem-spring-web
 */
@ControllerAdvice
class RestApiExceptionHandler : ProblemHandling, StradaExceptionHandler {

    override fun isCausalChainsEnabled(): Boolean = true

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<StradaExceptionHandler.ErrorResponse> {
        val response = StradaExceptionHandler.ErrorResponse(null, "Data is not found in DB")
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }
}
