package com.wnsgml972.strada.api.v1.product.noncoffee.service

data class NonCoffeeDTO(
    val id: String,
    val url: String,
    val price: Int,
    val description: String,
    val category: String,
) {
    constructor(id: String, nonCoffeeInsertRequest: NonCoffeeInsertRequest) : this(
        id,
        nonCoffeeInsertRequest.url,
        nonCoffeeInsertRequest.price,
        nonCoffeeInsertRequest.description,
        nonCoffeeInsertRequest.category
    )
}
