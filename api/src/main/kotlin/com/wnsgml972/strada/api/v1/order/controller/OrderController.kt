package com.wnsgml972.strada.api.v1.order.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.order.service.OrderDTO
import com.wnsgml972.strada.api.v1.order.service.OrderService
import com.wnsgml972.strada.api.v1.order.service.toDto
import com.wnsgml972.strada.api.v1.order.controller.OrderController.Companion.ORDER_BASE_URL
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * http status code 참고: https://sanghaklee.tistory.com/61
 */
@RestController
@RequestMapping(path = [ORDER_BASE_URL])
@Tag(
    name = "Order",
    description = """주문을 위한 API"""
)
class OrderController @Autowired constructor(
    private val orderService: OrderService
) {
    @GetMapping
    @ApiResponse(responseCode = "200", description = "List all Order")
    fun findAll() = orderService.selectAll()

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Find Order")
    fun find(@PathVariable("id") id: Long) = orderService.selectById(id)

    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Create Order")
    fun create(@RequestBody orderDTO: OrderDTO) = orderService.insert(orderDTO).toDto()

    @PutMapping("/")
    @ApiResponse(responseCode = "201", description = "Update Order")
    fun update(@RequestBody orderDTO: OrderDTO) = orderService.update(orderDTO).toDto()

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Delete Order")
    fun delete(@PathVariable("id") id: Long) = orderService.delete(id)

    companion object : KLogging() {
        private const val ORDER_SERVICE_NAME = "order"
        const val ORDER_BASE_URL = "$BASE_URL_V1/$ORDER_SERVICE_NAME"
    }
}
