package com.wnsgml972.strada.api.v1.product.bean.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.product.bean.service.BeanDTO
import com.wnsgml972.strada.api.v1.product.bean.service.BeanInsertRequest
import com.wnsgml972.strada.api.v1.product.bean.service.BeanService
import com.wnsgml972.strada.api.v1.product.bean.service.toDto
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
@RequestMapping(path = [BeanController.BEAN_BASE_URL])
@Tag(
    name = "beans",
    description = """Bean을 위한 API"""
)
class BeanController@Autowired constructor(
    private val beanService: BeanService
) {

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List all coffees")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun selectAll() = beanService.selectAll()

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Find coffee from id")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun select(@PathVariable("id") id: String) = beanService.selectById(id)

    @PostMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Add coffee")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun insert(@PathVariable id: String, @RequestBody @Valid beanInsertRequest: BeanInsertRequest): BeanDTO =
        beanService.insert(BeanDTO(id, beanInsertRequest)).toDto()

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Update coffee")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun update(@PathVariable id: String, @RequestBody @Valid beanInsertRequest: BeanInsertRequest): BeanDTO =
        beanService.update(BeanDTO(id, beanInsertRequest)).toDto()

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "delete bean")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun delete(@PathVariable id: String) = beanService.delete(id)

    companion object : KLogging() {
        private const val BEAN_SERVICE_NAME = "beans/admin"
        const val BEAN_BASE_URL = "$BASE_URL_V1/$BEAN_SERVICE_NAME"
    }
}
