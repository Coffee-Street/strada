package com.wnsgml972.strada.handler

import com.wnsgml972.strada.exception.StradaBadRequestException
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import com.wnsgml972.strada.exception.StradaUnAuthorizedException
import mu.KLogging
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.dao.ConcurrencyFailureException
import java.lang.reflect.Method

class StradaAsyncExceptionHandler : AsyncUncaughtExceptionHandler {

    override fun handleUncaughtException(ex: Throwable, method: Method, vararg args: Any?) {
        when (ex) {
            is ConcurrencyFailureException -> {
                logger.info(ex) { "ConcurrencyFailureException in @Async task" }
            }
            is StradaUnAuthorizedException -> {
                logger.debug(ex) { "StradaUnAuthorizedException in @Async task" }
            }
            is StradaBadRequestException -> {
                logger.warn(ex) { "StradaBadRequestException in @Async task" }
            }
            is StradaNotFoundException -> {
                logger.warn(ex) { "StradaNotFoundException in @Async task" }
            }
            is StradaIllegalStateException -> {
                logger.error(ex) { "StradaIllegalStateException in @Async task" }
            }
            else -> {
                logger.error(ex) { "@Async 처리 중 예상하지 못한 Exception이 발생했습니다." }
            }
        }
    }

    companion object : KLogging()
}
