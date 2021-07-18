package com.wnsgml972.strada.api.v1.item.coffee.controller.admin

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.item.coffee.service.*
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
    private val beanService: BeanService
) {

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List all coffees")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun selectAll() = coffeeService.selectAll()

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Find coffee from id")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun select(@PathVariable("id") id: String) = coffeeService.selectById(id)

    @PostMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Add coffee")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun insert(@PathVariable id: String, @RequestBody @Valid coffeeInsertRequest: CoffeeInsertRequest): CoffeeDTO =
        coffeeService.insert(CoffeeDTO(id, coffeeInsertRequest)).toDto()

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Update coffee")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun update(@PathVariable id: String, @RequestBody @Valid coffeeInsertRequest: CoffeeInsertRequest): CoffeeDTO =
        coffeeService.update(CoffeeDTO(id, coffeeInsertRequest)).toDto()

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "delete coffee")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun delete(@PathVariable id: String) = coffeeService.delete(id)

    @DeleteMapping("/bean/{id}")
    @ApiResponse(responseCode = "200", description = "delete bean")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    fun deleteBean(@PathVariable id: String) = beanService.delete(id)

    companion object : KLogging() {
        private const val ACCOUNT_SERVICE_NAME = "coffees/admin"
        const val COFFEE_BASE_URL = "$BASE_URL_V1/$ACCOUNT_SERVICE_NAME"
    }
}
