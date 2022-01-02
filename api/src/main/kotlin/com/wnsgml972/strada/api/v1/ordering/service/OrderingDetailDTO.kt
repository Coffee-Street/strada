package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.option.bean.service.BeanOptionDTO
import com.wnsgml972.strada.api.v1.option.bread.service.BreadOptionDTO
import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionDTO

data class OrderingDetailDTO(
    val id: Long,
    val coffeeName: String?,
    val nonCoffeeId: String?,
    val breadId: String?,
    val beanId: String?,
    val drinkOption: DrinkOptionDTO?,
    val breadOption: BreadOptionDTO?,
    val beanOption: BeanOptionDTO?,
)
