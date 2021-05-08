package com.wnsgml972.strada.api.v1.order.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderDetailRepository : JpaRepository<OrderDetail, Long>
