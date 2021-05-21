package com.wnsgml972.strada.api

import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "Index",
    description = """ping -> pong"""
)
@RestController
@RequestMapping(path = [IndexController.INDEX_BASE_URL])
class IndexController {

    @GetMapping
    fun ping() = INDEX_RESULT_VAL

    companion object : KLogging() {
        const val INDEX_BASE_URL = "/ping"
        const val INDEX_RESULT_VAL = "PONG"
    }
}
