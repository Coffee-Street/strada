package com.wnsgml972.strada.api.v1.item.service

import com.wnsgml972.strada.api.v1.item.domain.BeanCoffee


data class CoffeeDTO(

    val id: String,
    val url: String,
    val price: Int,
    val description: String,
    val category: String,
    val bean: BeanCoffee,
)
