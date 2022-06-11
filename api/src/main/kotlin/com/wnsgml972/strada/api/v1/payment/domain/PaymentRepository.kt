package com.wnsgml972.strada.api.v1.payment.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PaymentRepository : JpaRepository<Payment, Long> {
    fun findByTid(tid: String): Optional<Payment>
}
