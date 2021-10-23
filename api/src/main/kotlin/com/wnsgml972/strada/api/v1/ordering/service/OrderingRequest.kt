package com.wnsgml972.strada.api.v1.ordering.service

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class OrderingRequest(
    @NotNull(message="주문 상태는 필수")
    val status: OrderingStatus,

    @NotNull(message = "주문 상세 목록은 필수")
    @Min(1, message = "주문 상세 목록은 1개 이상 필요")
    val orderingDetailRequests: List<OrderingDetailRequest>
)
