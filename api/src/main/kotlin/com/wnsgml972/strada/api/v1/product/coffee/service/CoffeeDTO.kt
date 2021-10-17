package com.wnsgml972.strada.api.v1.product.coffee.service

import com.wnsgml972.strada.api.v1.product.bean.service.BeanDTO

data class CoffeeDTO(
    val name: String,
    val imageUrl: String,
    val price: Int,
    val description: String,
    val category: String,
    val beans: List<BeanDTO>
)