package com.wnsgml972.strada.api.v1.item.coffee.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.item.coffee.service.CoffeeDTO
import com.wnsgml972.strada.api.v1.item.coffee.service.CoffeeInsertRequest
import com.wnsgml972.strada.api.v1.item.coffee.service.CoffeeService
import com.wnsgml972.strada.api.v1.item.coffee.service.toDto
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

// junit5를 기반으로 테스트 짤 계획
// mock framework
// domain name: item
// Bread, Coffee, NonCoffee 패키지 나누
@RestController
@RequestMapping(path = [CoffeeController.COFFEE_BASE_URL])
@Tag(
    name = "coffees",
    description = """메뉴를 위한 API"""
)
class CoffeeController @Autowired constructor(
    private val coffeeService: CoffeeService
) {

    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "List all coffees")
    fun selectAll() = coffeeService.selectAll()

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Find coffee from id")
    fun select(@PathVariable("id") id: String) = coffeeService.selectById(id)

    @PostMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Add coffee")
    fun insert(@PathVariable id: String, @RequestBody @Valid coffeeInsertRequest: CoffeeInsertRequest): CoffeeDTO =
        coffeeService.insert(CoffeeDTO(id, coffeeInsertRequest)).toDto()

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Update coffee")
    fun update(@PathVariable id: String, @RequestBody @Valid coffeeInsertRequest: CoffeeInsertRequest): CoffeeDTO =
        coffeeService.update(CoffeeDTO(id, coffeeInsertRequest)).toDto()

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "delete coffee")
    fun delete(@PathVariable id: String) = coffeeService.delete(id)

    companion object : KLogging() {
        private const val ACCOUNT_SERVICE_NAME = "coffees/admin"
        const val COFFEE_BASE_URL = "$BASE_URL_V1/$ACCOUNT_SERVICE_NAME"
    }
}
