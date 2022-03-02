package com.wnsgml972.strada.api.v1.purchase.service

import com.wnsgml972.strada.api.v1.purchase.domain.Purchase
import com.wnsgml972.strada.api.v1.purchase.domain.PurchaseRepository
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository
) {
    @Transactional(readOnly = true)
    fun findAll(userId: String): List<PurchaseDTO> {
        return purchaseRepository
            .findAll()
            .map { it.toDto() }
    }

    @Transactional(readOnly = true)
    fun find(userId: String, id: Long): PurchaseDTO {
        return load(userId).toDto()
    }

    private fun load(userId: String): Purchase =
        purchaseRepository
            .findByUserId(userId)
            ?.let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            } ?: throw StradaNotFoundException("$userId's entity is Not Found")
}