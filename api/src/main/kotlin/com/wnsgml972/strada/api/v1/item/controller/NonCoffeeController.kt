package com.wnsgml972.strada.api.v1.item.controller

import BASE_URL_V1

import com.wnsgml972.strada.api.v1.item.service.NonCoffeeDTO
import com.wnsgml972.strada.api.v1.item.service.NonCoffeeService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

// junit5를 기반으로 테스트 짤 계획
// mock framework
// domain name: item

@RestController
// @RequestMapping(path = [MENU_BASE_URL])
@Tag(
    name = "item",
    description = """메뉴를 위한 API"""
)
class NonCoffeeController @Autowired constructor() {

    @Autowired
    private lateinit var nonCoffeeService: NonCoffeeService

    @PostMapping(MENU_BASE_URL + "/NonCoffees", produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "List all NonCoffees")
    fun listCoffeeAPI(): ResponseEntity<Any> {
        return ResponseEntity
            .ok()
            .body(nonCoffeeService.selectAll())
    }

    @GetMapping(value = [MENU_BASE_URL + "/NonCoffees/{id}"], produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "Find NonCoffees from id")
    fun findCoffeeAPI(@PathVariable("id") id: String): ResponseEntity<Any> {

        return ResponseEntity
            .ok()
            .body(nonCoffeeService.selectById(id))
    }
    @PostMapping(value = [MENU_BASE_URL + "/NonCoffees/{id}"], produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "Add NonCoffees")
    fun addCoffeeAPI(@RequestBody nonCoffeeDTO: NonCoffeeDTO): ResponseEntity<Any> {

        nonCoffeeService.insert(nonCoffeeDTO)
        return ResponseEntity
            .ok()
            .body(true)
    }

    companion object : KLogging() {
        private const val ACCOUNT_SERVICE_NAME = "items"
        const val MENU_BASE_URL = "$BASE_URL_V1/$ACCOUNT_SERVICE_NAME"
    }
}
