package com.wnsgml972.strada.api.v1.option.bread.service

import com.wnsgml972.strada.api.v1.ordering.service.OrderingDetailDTO

data class BreadOptionDTO(
    val id: Long,
    val orderingDetail: OrderingDetailDTO,
    val hereOrToGo: HereOrToGo,
    val forkCount: Int,
) {
    constructor(id: Long?, orderingDetail: OrderingDetailDTO, hereOrToGo: HereOrToGo, forkCount: Int) : this(
    id ?: 0,
    orderingDetail,
    hereOrToGo,
    forkCount
    )
}
