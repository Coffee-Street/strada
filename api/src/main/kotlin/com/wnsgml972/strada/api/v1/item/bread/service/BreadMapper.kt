package com.wnsgml972.strada.api.v1.item.bread.service

import com.wnsgml972.strada.api.v1.item.bread.domain.Bread
import com.wnsgml972.strada.exception.StradaBadRequestException

fun Bread.toDto() = BreadDTO(
    this.id ?: throw StradaBadRequestException("$id is not null"),
    this.imageUrl,
    this.price,
    this.description,
    this.category,
)

fun Bread.toBannerDto() = BreadBannerDTO(
    this.id ?: throw StradaBadRequestException("$id is not null"),
    this.imageUrl,
    this.description
)

fun BreadDTO.toEntity() = Bread(
    this.id,
    this.imageUrl,
    this.price,
    this.description,
    this.category,
)

fun BreadInsertRequest.toDto(id: String) = BreadDTO(
    id,
    imageUrl,
    price,
    description,
    category,
)
