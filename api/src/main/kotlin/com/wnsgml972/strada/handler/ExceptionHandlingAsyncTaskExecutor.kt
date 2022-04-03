package com.wnsgml972.strada.handler

import mu.KLogging
import org.springframework.core.task.AsyncTaskExecutor
import java.util.concurrent.Callable
import java.util.concurrent.Future

class ExceptionHandlingAsyncTaskExecutor(
    private val executor: AsyncTaskExecutor
) : AsyncTaskExecutor {

    override fun execute(task: Runnable) {
        executor.execute(task)
    }

    override fun execute(task: Runnable, startTimeout: Long) {
        executor.execute(task, startTimeout)
    }

    override fun submit(task: Runnable): Future<*> {
        return executor.submit(task)
    }

    override fun <T> submit(task: Callable<T>): Future<T> {
        return executor.submit(task)
    }

    companion object : KLogging()
}
