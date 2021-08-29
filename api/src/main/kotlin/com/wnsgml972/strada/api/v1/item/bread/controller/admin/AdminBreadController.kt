package com.wnsgml972.strada.api.v1.item.bread.controller.admin

import com.wnsgml972.strada.api.v1.item.bread.controller.BreadController.Companion.BREAD_BASE_URL
import com.wnsgml972.strada.api.v1.item.bread.service.AdminBreadService
import com.wnsgml972.strada.api.v1.item.bread.service.BreadInsertRequest
import com.wnsgml972.strada.api.v1.item.bread.service.toDto
import com.wnsgml972.strada.config.management.SpringdocOpenApiConfig
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import javax.validation.Valid

@RestController
@RequestMapping(path = [AdminBreadController.ADMIN_BREAD_BASE_URL])
@Tag(
    name = "breads",
    description = """아이템을 위한 API, bread 컨트롤"""
)
class AdminBreadController @Autowired constructor(
    private val adminBreadService: AdminBreadService
) {

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List all Bread")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun selectAll() =
        adminBreadService
            .selectAll()

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Find Bread")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun select(
        @PathVariable("id") id: String
    ) =
        adminBreadService
            .selectById(id)

    @PostMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Add Bread")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun insert(
        @PathVariable id: String,
        @RequestBody @Valid breadInsertRequest: BreadInsertRequest
    ) =
        adminBreadService
            .insert(breadInsertRequest.toDto(id))
            .toDto()

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Update Bread")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun update(
        @PathVariable id: String,
        @RequestBody @Valid breadInsertRequest: BreadInsertRequest
    ) =
        adminBreadService
            .update(breadInsertRequest.toDto(id))
            .toDto()

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Delete Bread")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun delete(
        @PathVariable id: String
    ) =
        adminBreadService
            .delete(id)

    companion object : KLogging() {
        const val ADMIN_BREAD_BASE_URL = "$BREAD_BASE_URL/admin"
    }
}
