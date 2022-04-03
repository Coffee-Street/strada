package com.wnsgml972.strada.config

import com.wnsgml972.strada.handler.ExceptionHandlingAsyncTaskExecutor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
@SuppressWarnings("MagicNumber")
class ExecutorConfig {

    @Bean
    fun loginEventHandlerExecutor(): ExceptionHandlingAsyncTaskExecutor {
        return ThreadPoolTaskExecutor()
            .apply {
                corePoolSize = 30
                maxPoolSize = 100
                setQueueCapacity(5000)
                setThreadNamePrefix("login-executor")
                initialize()
            }
            .let { ExceptionHandlingAsyncTaskExecutor(it) }
    }
}
