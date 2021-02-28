package com.wnsgml972.strada.api.v1.menu.controller


import BASE_URL_V1

import com.wnsgml972.strada.api.v1.menu.service.MenuService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
//@RequestMapping(path = [MENU_BASE_URL])
@Tag(
    name = "Menu",
    description = """메뉴를 위한 API"""
)
class MenuController @Autowired constructor(
){
    @Autowired
    private lateinit var menuService: MenuService


//    @PostMapping(MENU_BASE_URL,produces = ["application/json"])
//    @ApiResponse(responseCode = "200", description = "Test API")
//    fun testAPI(): TestDTO{
//        return TestDTO("test");
//    }

    @PostMapping(MENU_BASE_URL+"/all",produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "Test API")
    fun menuListAPI() : ResponseEntity<Any>{
        return ResponseEntity
            .ok()
            .body(menuService.findAll());
    }

    @GetMapping(value = [MENU_BASE_URL+"/{id}"], produces = ["application/json"])
    @ApiResponse(responseCode = "200", description = "Test API")
    fun menuFindAPI(@PathVariable("id") id:String) : ResponseEntity<Any>{

        return ResponseEntity
            .ok()
            .body(menuService.findById(id));
    }


    companion object : KLogging() {
        private const val ACCOUNT_SERVICE_NAME = "menu"
        const val MENU_BASE_URL = "$BASE_URL_V1/$ACCOUNT_SERVICE_NAME"
        const val SECONDS_TO_ADD: Long = 3600
    }
}


