package com.wnsgml972.strada.api.v1.item.coffee.service

import com.wnsgml972.strada.api.v1.item.coffee.domain.BeanCoffee
import com.wnsgml972.strada.api.v1.item.coffee.domain.Coffee

fun Coffee.toDto(): CoffeeDTO {

    var result = CoffeeDTO(
        this.id,
        this.url,
        this.price,
        this.description,
        this.category,

    )
    this.beanCoffees?.forEach { v -> v.bean?.let { result.bean.add(it) } }
    return result
}

fun CoffeeDTO.toEntity(): Coffee {

    var coffee = Coffee(
        this.id,
        this.url,
        this.price,
        this.description,
        this.category
    )

    var beanCoffee = ArrayList<BeanCoffee>()
    this.bean.forEach { v -> beanCoffee.add(BeanCoffee(0, coffee, v)) }
    coffee.beanCoffees = beanCoffee
    return coffee
}
