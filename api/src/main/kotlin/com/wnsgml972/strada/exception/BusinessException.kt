package com.wnsgml972.strada.exception

open class BusinessException(
    val code: String,
    override val message: String? = null
) : RuntimeException(message)
