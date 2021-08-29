package com.wnsgml972.strada.api.v1.item.bread.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.item.bread.service.BreadService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping(path = [BreadController.BREAD_BASE_URL])
@Tag(
    name = "breads",
    description = """breads를 위한 API"""
)
class BreadController @Autowired constructor(
    private var breadService: BreadService
) {

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Find Bread")
    fun select(
        @PathVariable("id") id: String
    ) =
        breadService
            .selectById(id)

    companion object : KLogging() {
        private const val BREAD_SERVICE_NAME = "breads"
        const val BREAD_BASE_URL = "$BASE_URL_V1/$BREAD_SERVICE_NAME"
    }
}
