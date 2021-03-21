package com.wnsgml972.strada.api.v1.item.service

import com.wnsgml972.strada.api.v1.item.domain.Coffee

fun Coffee.toDto() = CoffeeDTO(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
    this.beanCoffees,
)
fun CoffeeDTO.toEntity() = Coffee(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
    this.beanCoffees
)
