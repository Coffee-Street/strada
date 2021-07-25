package com.wnsgml972.strada.api.v1.product.noncoffee.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.product.noncoffee.service.NonCoffeeBannerService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = [NonCoffeeBannerController.BANNER_BASE_URL])
@Tag(
    name = "banners",
    description = """banner를 위한 API"""
)
class NonCoffeeBannerController @Autowired constructor(
    private var nonCoffeeBannerService: NonCoffeeBannerService
) {

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Find NonCoffees from id")
    fun select(@PathVariable("id") id: String) = nonCoffeeBannerService.selectById(id)

    companion object : KLogging() {
        private const val BANNER_SERVICE_NAME = "noncoffees/banners"
        const val BANNER_BASE_URL = "$BASE_URL_V1/$BANNER_SERVICE_NAME"
    }
}
