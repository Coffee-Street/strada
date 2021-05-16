package com.wnsgml972.strada.api.v1.item.bread.service

import com.wnsgml972.strada.api.v1.item.bread.domain.Bread

fun Bread.toDto() = BreadDTO(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
)
fun Bread.toBannerDto() = BreadBannerDTO(
    this.id,
    this.url,
    this.description
)
fun BreadDTO.toEntity() = Bread(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
)
