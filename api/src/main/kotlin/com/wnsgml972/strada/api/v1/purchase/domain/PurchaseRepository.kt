package com.wnsgml972.strada.api.v1.purchase.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseRepository : JpaRepository<Purchase, Long> {
    fun findByUserId(userId: String): Purchase?
}