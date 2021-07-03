package com.wnsgml972.strada.api.v1.ordering.service

import java.time.LocalDateTime

data class OrderingDTO(
    val id: Long,
    val status: OrderingStatus,
    val createdAt: LocalDateTime
)
