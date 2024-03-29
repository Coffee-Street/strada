package com.wnsgml972.strada.api.v1.product.coffee.domain

import com.wnsgml972.strada.api.base.ValueObject

data class BeanCoffeeId(
    val coffee: Long = 0,
    val bean: String = "",
) : ValueObject
