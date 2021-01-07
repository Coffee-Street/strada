package com.wnsgml972.strada.handler

import org.springframework.web.bind.annotation.ControllerAdvice
import org.zalando.problem.spring.web.advice.ProblemHandling

/**
 * Problem 라이브러리를 이용하여 REST API 에서 발생한 예외 정보를 Client에게 전송해주는 Handler 입니다.
 *
 * 참고: https://github.com/zalando/problem-spring-web
 */
@ControllerAdvice
class RestApiExceptionHandler : ProblemHandling, StradaExceptionHandler {

    override fun isCausalChainsEnabled(): Boolean = true
}
