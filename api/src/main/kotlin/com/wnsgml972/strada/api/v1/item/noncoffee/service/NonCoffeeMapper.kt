package com.wnsgml972.strada.api.v1.item.noncoffee.service

import com.wnsgml972.strada.api.v1.item.noncoffee.domain.NonCoffee
import com.wnsgml972.strada.exception.StradaBadRequestException

fun NonCoffee.toDto() = NonCoffeeDTO(
    this.id ?: throw StradaBadRequestException("$id is not null"),
    this.imageUrl,
    this.price,
    this.description,
    this.category,
)
fun NonCoffee.toBannerDto() = NonCoffeeBannerDTO(
    this.id ?: throw StradaBadRequestException("$id is not null"),
    this.imageUrl,
    this.description
)
fun NonCoffeeDTO.toEntity() = NonCoffee(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
)
