package com.wnsgml972.strada.api.v1.order.service

import com.wnsgml972.strada.api.v1.order.domain.OrderDetail
import java.time.LocalDateTime

data class OrderDTO(
    val id: Long,
    val status: OrderStatus,
    val createdAt: LocalDateTime,
    val orderDetails: List<OrderDetail>
)
