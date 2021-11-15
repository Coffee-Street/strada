package com.wnsgml972.strada.api.v1.product.coffee.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeDTO
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeInsertRequest
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeService
import com.wnsgml972.strada.api.v1.product.coffee.service.toCoffeeDto
import com.wnsgml972.strada.api.v1.product.coffee.service.toEntity
import com.wnsgml972.strada.config.management.SpringdocOpenApiConfig
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import javax.validation.Valid

@RestController
@RequestMapping(path = [CoffeeController.COFFEE_BASE_URL])
@Tag(
    name = "coffees",
    description = """메뉴를 위한 API"""
)
class CoffeeController @Autowired constructor(
    private val coffeeService: CoffeeService,
) {

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List all coffees")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun selectAll() =
        coffeeService.selectAll()

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Find coffee from id")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun select(@PathVariable("id") name: String): CoffeeDTO =
        coffeeService.select(name)

    @PostMapping("/{name}")
    @ApiResponse(responseCode = "200", description = "Add coffee")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun insert(@PathVariable name: String, @RequestBody @Valid coffeeInsertRequest: CoffeeInsertRequest): CoffeeDTO =
        coffeeService.insert(name, coffeeInsertRequest)

    @PutMapping("/{name}")
    @ApiResponse(responseCode = "200", description = "Update coffee")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun update(@PathVariable name: String, @RequestBody @Valid coffeeInsertRequest: CoffeeInsertRequest): CoffeeDTO =
        coffeeService.update(name, coffeeInsertRequest)

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "delete coffee")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun delete(@PathVariable id: String) =
        coffeeService.delete(id)

    companion object : KLogging() {
        private const val COFFEE_SERVICE_NAME = "coffees/admin"
        const val COFFEE_BASE_URL = "$BASE_URL_V1/$COFFEE_SERVICE_NAME"
    }
}
