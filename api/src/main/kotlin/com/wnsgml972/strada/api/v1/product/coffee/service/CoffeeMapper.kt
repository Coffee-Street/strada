package com.wnsgml972.strada.api.v1.product.coffee.service

import com.wnsgml972.strada.api.v1.product.bean.service.toDto
import com.wnsgml972.strada.api.v1.product.bean.service.toEntity
import com.wnsgml972.strada.api.v1.product.coffee.domain.BeanCoffee
import com.wnsgml972.strada.api.v1.product.coffee.domain.Coffee

fun Coffee.toDto() = CoffeeDTO(
    this.id,
    this.name,
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

fun CoffeeDTO.toEntity() = Coffee(
    0,
    this.name,
    this.imageUrl,
    this.price,
    this.description,
    this.category,
).let { coffee ->
    coffee.beanCoffees = this.beans.map { v -> BeanCoffee(coffee, v.toEntity()) }.toList()
    coffee
}

fun CoffeeInsertRequest.toEntity(name: String, id: Long? = 0) = Coffee(
    id,
    name,
    this.imageUrl,
    this.price,
    this.description,
    this.category,
).let { coffee ->
    coffee.beanCoffees = this.beans.map { v -> BeanCoffee(coffee, v.toEntity()) }.toList()
    coffee
}
fun CoffeeInsertRequest.toEntity(id: Long? = 0, name: String) = Coffee(
    id,
    name,
    this.imageUrl,
    this.price,
    this.description,
    this.category,
).let { coffee ->
    coffee.beanCoffees = this.beans.map { v -> BeanCoffee(coffee, v.toEntity()) }.toList()
    coffee
}
