package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.product.bean.service.BeanDTO
import com.wnsgml972.strada.api.v1.product.bread.service.BreadDTO
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeDTO
import com.wnsgml972.strada.api.v1.product.noncoffee.service.NonCoffeeDTO
import com.wnsgml972.strada.api.v1.option.bean.service.BeanOptionDTO
import com.wnsgml972.strada.api.v1.option.bread.service.BreadOptionDTO
import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionDTO

data class OrderingDetailDTO(
    val id: Long,
    val coffee: CoffeeDTO?,
    val nonCoffee: NonCoffeeDTO?,
    val bread: BreadDTO?,
    val bean: BeanDTO?,
    val drinkOption: DrinkOptionDTO?,
    val breadOption: BreadOptionDTO?,
    val beanOption: BeanOptionDTO?,
)
