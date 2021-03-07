package com.wnsgml972.strada.api.v1.item.controller


import BASE_URL_V1
import com.wnsgml972.strada.api.v1.item.service.CoffeeDTO

import com.wnsgml972.strada.api.v1.item.service.CoffeeService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



// junit5를 기반으로 테스트 짤 계획
// mock framework
// domain name: item

@RestController
//@RequestMapping(path = [MENU_BASE_URL])
@Tag(
    name = "item",
    description = """메뉴를 위한 API"""
)
class CoffeeController @Autowired constructor(
){

    @Autowired
    private lateinit var coffeeService: CoffeeService



    @PostMapping(MENU_BASE_URL+"/coffees",produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "List all coffees")
    fun listCoffeeAPI() : ResponseEntity<Any>{
        return ResponseEntity
            .ok()
            .body(coffeeService.findAll());
    }

    @GetMapping(value = [MENU_BASE_URL+"/coffees/{id}"], produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "Find coffee from id")
    fun findCoffeeAPI(@PathVariable("id") id:String) : ResponseEntity<Any>{


        return ResponseEntity
            .ok()
            .body(coffeeService.findById(id))
    }
    @PostMapping(value = [MENU_BASE_URL+"/coffees/{id}"], produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "Add coffee")
    fun addCoffeeAPI(@RequestBody  coffeeDTO: CoffeeDTO) : ResponseEntity<Any>{

        coffeeService.save(coffeeDTO)
        return ResponseEntity
            .ok()
            .body(true)
    }


    companion object : KLogging() {
        private const val ACCOUNT_SERVICE_NAME = "items"
        const val MENU_BASE_URL = "$BASE_URL_V1/$ACCOUNT_SERVICE_NAME"
    }
}


