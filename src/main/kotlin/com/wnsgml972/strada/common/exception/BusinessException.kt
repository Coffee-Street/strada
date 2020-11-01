package com.wnsgml972.strada.common.exception

open class BusinessException(
    val code: String,
    override val message: String? = null
): RuntimeException(message)