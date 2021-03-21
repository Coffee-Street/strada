package com.wnsgml972.strada.api.v1.item.service

import com.wnsgml972.strada.api.v1.item.domain.Bread

fun Bread.toDto() = BreadDTO(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
)
fun BreadDTO.toEntity() = Bread(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
)
