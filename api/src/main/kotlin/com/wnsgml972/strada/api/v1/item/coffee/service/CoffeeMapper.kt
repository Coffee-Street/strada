package com.wnsgml972.strada.api.v1.item.coffee.service

import com.wnsgml972.strada.api.v1.item.coffee.domain.BeanCoffee
import com.wnsgml972.strada.api.v1.item.coffee.domain.Coffee
import com.wnsgml972.strada.exception.StradaBadRequestException

fun Coffee.toDto() = CoffeeDTO(
    this.id ?: throw StradaBadRequestException("$id is not null"),
    this.imageUrl,
    this.price,
    this.description,
    this.category,
    this.beanCoffees
        .let {
            return@let it
                .mapNotNull { v -> v.bean?.toDto() }
                .toList()
        }
)

fun Coffee.toBannerDto() = CoffeeBannerDTO(
    this.id ?: throw StradaBadRequestException("$id is not null"),
    this.imageUrl,
    this.description
)

fun CoffeeDTO.toEntity() = Coffee(
    this.id,
    this.imageUrl,
    this.price,
    this.description,
    this.category,
).let { coffee ->
    coffee.beanCoffees = this.beans.map { v -> BeanCoffee(coffee, v.toEntity()) }.toList()
    coffee
}
