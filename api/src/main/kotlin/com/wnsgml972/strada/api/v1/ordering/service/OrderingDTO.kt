package com.wnsgml972.strada.api.v1.ordering.service

import java.time.LocalDateTime

data class OrderingDTO(
    val id: Long,
    val status: OrderingStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val orderingDetails: List<OrderingDetailDTO>
) {
    constructor(id: Long?, orderingRequest: OrderingRequest) : this(
        id ?: 0,
        orderingRequest.status,
        orderingRequest.createdAt ?: LocalDateTime.now(),
        orderingRequest.updatedAt ?: LocalDateTime.now(),
        orderingRequest.orderingDetails
    )
}
