package com.wnsgml972.strada.api.v1.product.coffee.service

data class CoffeeDTO(
    val name: String,
    val imageUrl: String,
    val price: Int,
    val description: String,
    val category: String,
    val beans: List<BeanDTO>
) {
    constructor(name: String, coffeeInsertRequest: CoffeeInsertRequest) : this(
        name,
        coffeeInsertRequest.imageUrl,
        coffeeInsertRequest.price,
        coffeeInsertRequest.description,
        coffeeInsertRequest.category,
        coffeeInsertRequest.beans
    )
}
