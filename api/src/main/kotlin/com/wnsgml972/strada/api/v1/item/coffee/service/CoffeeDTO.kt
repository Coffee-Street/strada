package com.wnsgml972.strada.api.v1.item.coffee.service

data class CoffeeDTO(
    val id: String,
    val imageUrl: String,
    val price: Int,
    val description: String,
    val category: String,
    val beans: List<BeanDTO>
) {
    constructor(id: String, coffeeInsertRequest: CoffeeInsertRequest) : this(
        id,
        coffeeInsertRequest.imageUrl,
        coffeeInsertRequest.price,
        coffeeInsertRequest.description,
        coffeeInsertRequest.category,
        coffeeInsertRequest.beans
    )
}
