package com.wnsgml972.strada.api.v1.product.coffee.service

import com.wnsgml972.strada.api.v1.product.bean.service.toDto
import com.wnsgml972.strada.api.v1.product.bean.service.toEntity
import com.wnsgml972.strada.api.v1.product.coffee.domain.BeanCoffee
import com.wnsgml972.strada.api.v1.product.coffee.domain.Coffee
import com.wnsgml972.strada.exception.BadRequestException

// fun Coffee.toDto() = CoffeeDTO(
//    this.id ?: throw BadRequestException("$id is not null"),
//    this.imageUrl,
//    this.price,
//    this.description,
//    this.category,
//    this.beanCoffees
//        .let {
//            return@let it
//                .mapNotNull { v -> v.bean?.toDto() }
//                .toList()
//        }
// )
//
// fun Coffee.toBannerDto() = CoffeeBannerDTO(
//    this.id ?: throw BadRequestException("$id is not null"),
//    this.imageUrl,
//    this.description
// )
//
// fun CoffeeDTO.toEntity() = Coffee(
//    this.id,
//    this.imageUrl,
//    this.price,
//    this.description,
//    this.category,
// ).let { coffee ->
//    coffee.beanCoffees = this.beans.map { v -> BeanCoffee(coffee, v.toEntity()) }.toList()
//    coffee
// }
//
// fun CoffeeDTO.toEntity(id: Long) = Coffee(
//    this.id,
//    this.imageUrl,
//    this.price,
//    this.description,
//    this.category,
// ).let { coffee ->
//    coffee.beanCoffees = this.beans.map { v -> BeanCoffee(coffee, v.toEntity()) }.toList()
//    coffee
// }

fun Coffee.toDto() = CoffeeDTO(
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

fun Coffee.toBannerDto() = CoffeeBannerDTO(
    this.name ?: throw BadRequestException("$id is not null"),
    this.imageUrl,
    this.description
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

fun CoffeeDTO.toEntity(id: Long) = Coffee(
    id,
    this.name,
    this.imageUrl,
    this.price,
    this.description,
    this.category,
).let { coffee ->
    coffee.beanCoffees = this.beans.map { v -> BeanCoffee(coffee, v.toEntity()) }.toList()
    coffee
}
