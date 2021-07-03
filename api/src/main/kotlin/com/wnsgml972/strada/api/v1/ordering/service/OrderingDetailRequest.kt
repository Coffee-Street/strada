package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.option.bean.service.BeanOptionDTO
import com.wnsgml972.strada.api.v1.option.bread.service.BreadOptionDTO
import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionDTO

data class OrderingDetailRequest(
    val drinkOptionDTO: DrinkOptionDTO?,
    val breadOptionDTO: BreadOptionDTO?,
    val beanOptionDTO: BeanOptionDTO?
)
