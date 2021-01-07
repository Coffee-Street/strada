package com.wnsgml972.strada.api

import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "Index Controller",
    description = """ping -> pong"""
)
@RestController
@RequestMapping
class IndexController {

    @GetMapping(path = [INDEX_BASE_URL])
    fun ping(): String = "PONG"

    companion object : KLogging() {
        const val INDEX_BASE_URL = "/ping"
    }
}
