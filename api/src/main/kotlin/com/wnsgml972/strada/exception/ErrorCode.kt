package com.wnsgml972.strada.exception

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ErrorCode(val value: Int)
