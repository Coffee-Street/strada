package com.wnsgml972.strada.api.v1.ordering.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.ordering.service.OrderingService
import com.wnsgml972.strada.api.v1.ordering.controller.OrderingController.Companion.ORDERING_BASE_URL
import com.wnsgml972.strada.api.v1.ordering.service.OrderingRequest
import com.wnsgml972.strada.api.v1.ordering.service.OrderingResponse
import com.wnsgml972.strada.api.v1.ordering.service.toDto
import com.wnsgml972.strada.config.management.SpringdocOpenApiConfig
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
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
import javax.validation.Valid

/**
 * http status code 참고: https://sanghaklee.tistory.com/61
 */
@RestController
@RequestMapping(path = [ORDERING_BASE_URL])
@Tag(
    name = "Order",
    description = """주문을 위한 API"""
)
class OrderingController @Autowired constructor(
    private val orderingService: OrderingService
) {
    @GetMapping
    @Operation(
        summary = "모든 주문 가져오기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)]
    )
    @ApiResponse(responseCode = "200", description = "List all Ordering")
    fun findAll() = orderingService.selectAll()

    @GetMapping("/{id}")
    @Operation(
        summary = "특정 주문 가져오기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)]
    )
    @ApiResponse(responseCode = "200", description = "Find Ordering")
    fun find(@PathVariable("id") id: Long) = orderingService.selectById(id)

    @PostMapping
    @Operation(
        summary = "주문하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)]
    )
    @ApiResponse(responseCode = "200", description = "Create Ordering")
    fun insert(@RequestBody @Valid orderingRequest: OrderingRequest): OrderingResponse =
        orderingService.insert(orderingRequest)

    @PutMapping("/{id}")
    @Operation(
        summary = "주문 갱신하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)]
    )
    @ApiResponse(responseCode = "200", description = "Update Order")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody @Valid orderingRequest: OrderingRequest
    ) =
        orderingService.update(orderingRequest.toDto(id))

    @DeleteMapping("/{id}")
    @Operation(
        summary = "주문 삭제하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)]
    )
    @ApiResponse(responseCode = "200", description = "Delete Order")
    fun delete(@PathVariable("id") id: Long) = orderingService.delete(id)

    companion object : KLogging() {
        private const val ORDERING_SERVICE_NAME = "ordering"
        const val ORDERING_BASE_URL = "$BASE_URL_V1/$ORDERING_SERVICE_NAME"
    }
}
