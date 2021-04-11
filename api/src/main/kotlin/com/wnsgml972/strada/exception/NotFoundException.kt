package com.wnsgml972.strada.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


open class NotFoundException : RuntimeException("Resource Not Found")
