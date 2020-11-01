package com.wnsgml972.strada.common.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/error")
class ErrorController {
    @GetMapping("/unauthorized")
    fun unauthorized(): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build<Any>()
    }
}