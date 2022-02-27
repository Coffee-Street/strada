package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.payment.domain.Payment
import com.wnsgml972.strada.api.v1.payment.domain.PaymentRepository
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service

class KakaoPaymentService(
    private val paymentRepository: PaymentRepository

) {

    @Transactional(readOnly = true)
    fun selectAll() =
        paymentRepository
            .findAll()
            .map { it.toDto() }
    @Transactional(readOnly = true)
    fun selectById(id: Long) =
        load(id)
            .toDto()

    @Transactional
    fun insert(paymentInsertRequest: PaymentInsertRequest) =
        paymentRepository
            .save(paymentInsertRequest.toEntity())
            .toDto()

    @Transactional
    fun update(id: Long, paymentInsertRequest: PaymentInsertRequest) =
        load(id)
            .let {
                paymentRepository.save(paymentInsertRequest.toEntity(id))
            }
            .toDto()

    @Transactional
    fun delete(id: Long) =
        load(id)
            .let {
                paymentRepository.delete(it)
            }

    @Transactional(readOnly = true)
    fun load(id: Long): Payment =
        paymentRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id Not Found") }
            .let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            }

    companion object : KLogging()
}
