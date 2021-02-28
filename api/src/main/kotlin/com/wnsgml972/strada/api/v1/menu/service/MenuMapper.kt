package com.wnsgml972.strada.api.v1.menu.service

import com.wnsgml972.strada.api.v1.menu.domain.CoffeeMenu

fun CoffeeMenu.toDto() = MenuDTO(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
)

