package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.option.bean.service.BeanOptionDTO
import com.wnsgml972.strada.api.v1.option.bread.service.BreadOptionDTO
import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionDTO

data class OrderingDetailDTO(
    val id: Long,
    val ordering: OrderingDTO,
//    val coffee : Coffee,
//    val nonCoffee : NonCoffee,
//    val bread : Bread,
//    val bean : Bean,
    val drinkOption: DrinkOptionDTO?,
    val breadOption: BreadOptionDTO?,
    val beanOption: BeanOptionDTO?,
)
