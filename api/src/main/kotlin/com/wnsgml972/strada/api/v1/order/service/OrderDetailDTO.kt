package com.wnsgml972.strada.api.v1.order.service

import com.wnsgml972.strada.api.v1.order.domain.Order
import com.wnsgml972.strada.api.v1.option.drink.domain.DrinkOption
import com.wnsgml972.strada.api.v1.option.bean.domain.BeanOption
import com.wnsgml972.strada.api.v1.option.bread.domain.BreadOption

data class OrderDetailDTO(
    val id: Long,
    val order: Order,
//    val coffee : Coffee,
//    val nonCoffee : NonCoffee,
//    val bread : Bread,
//    val bean : Bean,
    val drinkOption: DrinkOption?,
    val breadOption: BreadOption?,
    val beanOption: BeanOption?,
)
