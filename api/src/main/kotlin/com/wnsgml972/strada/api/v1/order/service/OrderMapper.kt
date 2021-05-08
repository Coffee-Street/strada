package com.wnsgml972.strada.api.v1.order.service

import com.wnsgml972.strada.api.v1.order.domain.Order
import com.wnsgml972.strada.exception.BadRequestException

fun Order.toDto() = OrderDTO(
    this.id ?: throw BadRequestException("$id must not null"),
    this.status,
    this.createdAt,
    this.orderDetails
)

fun OrderDTO.toEntity() = Order.of(
    this.status,
    this.orderDetails
)
