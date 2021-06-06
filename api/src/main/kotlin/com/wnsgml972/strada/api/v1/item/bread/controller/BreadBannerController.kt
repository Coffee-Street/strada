package com.wnsgml972.strada.api.v1.item.bread.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.item.bread.service.BreadBannerService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping(path = [BreadBannerController.BANNER_BASE_URL])
@Tag(
    name = "banners",
    description = """banner를 위한 API"""
)
class BreadBannerController @Autowired constructor(
    private var breadBannerService: BreadBannerService
) {

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Find Bread for banner")
    fun select(@PathVariable("id") id: String) = breadBannerService.selectById(id)

    companion object : KLogging() {
        private const val BANNER_SERVICE_NAME = "breads/banners"
        const val BANNER_BASE_URL = "$BASE_URL_V1/$BANNER_SERVICE_NAME"
    }
}
