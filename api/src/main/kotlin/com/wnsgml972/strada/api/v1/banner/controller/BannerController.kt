package com.wnsgml972.strada.api.v1.banner.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.banner.service.BannerInsertRequest
import com.wnsgml972.strada.api.v1.banner.service.BannerInsertResponse
import com.wnsgml972.strada.api.v1.banner.service.BannerService
import com.wnsgml972.strada.config.management.SpringdocOpenApiConfig
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(path = [BannerController.BANNER_BASE_URL])
@Tag(
    name = "banners",
    description = """banner를 위한 API"""
)
class BannerController @Autowired constructor(
    private var bannerService: BannerService
) {

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request all Banners")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun selectAll() =
        bannerService.selectAll()

    @GetMapping("/{code}")
    @ApiResponse(responseCode = "200", description = "Find one banner by eval order")
    fun select(@PathVariable("code") code: String) =
        bannerService.select(code)

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Add one banner")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun insert(@RequestBody @Valid bannerInsertRequest: BannerInsertRequest): BannerInsertResponse =
        bannerService
            .insert(bannerInsertRequest)

    @PutMapping
    @ApiResponse(responseCode = "200", description = "Update one banner")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun update(@RequestBody @Valid bannerInsertRequest: BannerInsertRequest) =
        bannerService
            .update(bannerInsertRequest)

    @DeleteMapping("/{code}")
    @ApiResponse(responseCode = "200", description = "delete one banner")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun delete(@PathVariable code: String) =
        bannerService
            .delete(code)

    companion object : KLogging() {
        private const val BANNER_SERVICE_NAME = "/banners"
        const val BANNER_BASE_URL = "$BASE_URL_V1/$BANNER_SERVICE_NAME"
    }
}
