package com.wnsgml972.strada.api.v1.option.bean.service

import com.wnsgml972.strada.api.v1.ordering.service.OrderingDetailDTO

data class BeanOptionDTO(
    val id: Long,
    val orderingDetail: OrderingDetailDTO,
    val grindType: GrindType,
) {
    constructor(id: Long?, orderingDetail: OrderingDetailDTO, grindType: GrindType) : this(
        id ?: 0,
        orderingDetail,
        grindType
    )
}
