package com.wnsgml972.strada.api.v1.ordering.service

import java.time.LocalDateTime

data class OrderingRequest(
    val status: OrderingStatus,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val orderingDetails: List<OrderingDetailDTO>
)
