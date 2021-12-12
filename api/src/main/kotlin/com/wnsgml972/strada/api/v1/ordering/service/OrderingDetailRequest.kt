package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.option.bean.service.BeanOptionRequest
import com.wnsgml972.strada.api.v1.option.bread.service.BreadOptionRequest
import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionRequest

data class OrderingDetailRequest(
    val coffeeName: String?,
    val nonCoffeeId: String?,
    val breadId: String?,
    val beanId: String?,
    val drinkOptionRequest: DrinkOptionRequest?,
    val breadOptionRequest: BreadOptionRequest?,
    val beanOptionRequest: BeanOptionRequest?,
)
