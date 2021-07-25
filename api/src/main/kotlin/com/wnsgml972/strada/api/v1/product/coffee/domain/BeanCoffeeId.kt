package com.wnsgml972.strada.api.v1.product.coffee.domain

import java.io.Serializable

data class BeanCoffeeId(
    val coffee: String = "",
    val bean: String = "",
) : Serializable
