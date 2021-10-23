package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.product.bean.service.BeanDTO
import com.wnsgml972.strada.api.v1.product.bread.service.BreadDTO
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeDTO
import com.wnsgml972.strada.api.v1.product.noncoffee.service.NonCoffeeDTO
import com.wnsgml972.strada.api.v1.option.bean.service.BeanOptionDTO
import com.wnsgml972.strada.api.v1.option.bread.service.BreadOptionDTO
import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionDTO

data class OrderingDetailRequest(
    val coffeeDTO: CoffeeDTO?,
    val nonCoffeeDTO: NonCoffeeDTO?,
    val breadDTO: BreadDTO?,
    val beanDTO: BeanDTO?,
    val drinkOptionDTO: DrinkOptionDTO?,
    val breadOptionDTO: BreadOptionDTO?,
    val beanOptionDTO: BeanOptionDTO?
)
