package com.wnsgml972.strada.api.v1.product.noncoffee.service

import com.wnsgml972.strada.api.v1.product.noncoffee.domain.NonCoffee
import com.wnsgml972.strada.exception.StradaBadRequestException

fun NonCoffee.toDto() = NonCoffeeDTO(
    this.id ?: throw StradaBadRequestException("$id is not null"),
    this.imageUrl,
    this.price,
    this.description,
    this.category,
)

fun NonCoffeeDTO.toEntity() = NonCoffee(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
)

fun NonCoffeeInsertRequest.toNonCoffeeDto(id: String) =
    NonCoffeeDTO(
        id,
        this.url,
        this.price,
        this.description,
        this.category
    )
