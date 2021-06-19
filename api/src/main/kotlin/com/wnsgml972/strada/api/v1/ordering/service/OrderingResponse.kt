package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.base.AbstractValueObject

data class OrderingResponse(
    val orderingDTO: OrderingDTO
) : AbstractValueObject() {

    override fun equalProperties(other: Any): Boolean {
        return other is OrderingResponse &&
                orderingDTO == other.orderingDTO
    }
}
