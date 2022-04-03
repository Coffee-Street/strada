package com.wnsgml972.strada.config

import com.wnsgml972.strada.handler.ExceptionHandlingAsyncTaskExecutor
import com.wnsgml972.strada.handler.StradaAsyncExceptionHandler
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurerSupport
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.Executor

@EnableAsync
@Configuration
class AsyncConfig @Autowired constructor(
    private val commonExecutor: ExceptionHandlingAsyncTaskExecutor
) : AsyncConfigurerSupport() {

    override fun getAsyncExecutor(): Executor {
        return commonExecutor
    }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler? {
        return StradaAsyncExceptionHandler()
    }
}
