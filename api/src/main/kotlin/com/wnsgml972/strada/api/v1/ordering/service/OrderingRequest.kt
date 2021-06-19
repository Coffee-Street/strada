package com.wnsgml972.strada.api.v1.ordering.service

data class OrderingRequest(
    val status: OrderingStatus,
    val orderingDetails: List<OrderingDetailDTO>
)
