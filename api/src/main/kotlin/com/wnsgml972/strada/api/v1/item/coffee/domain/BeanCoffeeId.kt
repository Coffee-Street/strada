package com.wnsgml972.strada.api.v1.item.coffee.domain

import com.wnsgml972.strada.api.base.ValueObject

data class BeanCoffeeId(
    val coffee: String = "",
    val bean: String = "",
) : ValueObject
