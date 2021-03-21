package com.wnsgml972.strada.api.v1.item.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.item.service.CoffeeDTO

import com.wnsgml972.strada.api.v1.item.service.CoffeeService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.DeleteMapping

// junit5를 기반으로 테스트 짤 계획
// mock framework
// domain name: item
// Bread, Coffee, NonCoffee 패키지 나누
@RestController
@RequestMapping(path = [CoffeeController.MENU_BASE_URL])
@Tag(
    name = "item",
    description = """메뉴를 위한 API"""
)
class CoffeeController @Autowired constructor(
    private val coffeeService: CoffeeService
) {

    @PostMapping("/coffees")
    @ApiResponse(responseCode = "200", description = "List all coffees")

    fun selectAll() = coffeeService.selectAll()

    @GetMapping(value = [MENU_BASE_URL + "/coffees/{id}"], produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "Find coffee from id")
    fun find(@PathVariable("id") id: String): ResponseEntity<Any> {

        return ResponseEntity
            .ok()
            .body(coffeeService.selectById(id))
    }
    @PostMapping(value = [MENU_BASE_URL + "/coffees/{id}"], produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "Add coffee")
    fun add(@RequestBody coffeeDTO: CoffeeDTO): ResponseEntity<Any> {

        coffeeService.insert(coffeeDTO)
        return ResponseEntity
            .ok()
            .body(true)
    }

    @DeleteMapping(value = [MENU_BASE_URL + "/coffees/{id}"], produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "delete coffee")
    fun delete(@PathVariable("id") id: String): ResponseEntity<Any> {

        coffeeService.delete(id)
        return ResponseEntity(HttpStatus.OK)

    }

    companion object : KLogging() {
        private const val ACCOUNT_SERVICE_NAME = "items"
        const val MENU_BASE_URL = "$BASE_URL_V1/$ACCOUNT_SERVICE_NAME"
    }
}
