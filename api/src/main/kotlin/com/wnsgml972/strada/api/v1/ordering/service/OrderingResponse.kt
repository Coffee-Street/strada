package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.base.AbstractValueObject

data class OrderingResponse(
    val orderingDTO: OrderingDTO,
    val orderingDetailDTOs: List<OrderingDetailDTO>
) : AbstractValueObject() {

    override fun equalProperties(other: Any): Boolean {
        return other is OrderingResponse &&
                orderingDTO == other.orderingDTO &&
                orderingDetailDTOs == other.orderingDetailDTOs
    }
}
