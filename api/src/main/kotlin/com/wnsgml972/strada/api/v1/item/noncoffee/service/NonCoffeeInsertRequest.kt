package com.wnsgml972.strada.api.v1.item.noncoffee.service

data class NonCoffeeInsertRequest(
    val url: String,
    val price: Int,
    val description: String,
    val category: String,
)
