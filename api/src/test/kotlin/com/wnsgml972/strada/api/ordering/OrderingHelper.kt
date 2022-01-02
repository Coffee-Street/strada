package com.wnsgml972.strada.api.ordering

import com.wnsgml972.strada.api.v1.ordering.domain.OrderingDetail
import com.wnsgml972.strada.api.v1.ordering.service.OrderingDetailRequest
import com.wnsgml972.strada.api.v1.ordering.service.OrderingRequest
import com.wnsgml972.strada.api.v1.ordering.service.OrderingService
import com.wnsgml972.strada.api.v1.ordering.service.OrderingStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderingHelper @Autowired constructor(
    private val orderingService: OrderingService,
) {
    fun createOrdering(status: OrderingStatus, orderingDetailRequests: List<OrderingDetailRequest>) =
        orderingService.insert(OrderingRequest(OrderingStatus.REQUEST, orderingDetailRequests))

    fun deleteOrdering(id: Long) =
        orderingService.delete(id)

    fun clearOrdering() =
        orderingService
            .selectAll()
            .map {
                orderingService.delete(it.id)
            }
}