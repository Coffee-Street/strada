package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.payment.domain.Payment
import com.wnsgml972.strada.api.v1.payment.domain.PaymentRepository
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import mu.KLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service

class KakaoPaymentService(
    private val paymentRepository: PaymentRepository,
    private val userRepository: UserRepository

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
    fun insert(userId: String, paymentReadyRequest: PaymentReadyRequest) =
        userRepository.findByIdOrNull(userId)
            ?.let {
                paymentRepository
                    .save(paymentReadyRequest.toEntity(it))
                    .toDto()
            } ?: throw StradaNotFoundException("$userId is not found")

    @Transactional
    fun insert(userId: String, paymentApproveRequest: PaymentApproveRequest) =
        userRepository.findByIdOrNull(userId)
            ?.let {
            paymentRepository
                .save(paymentApproveRequest.toEntity(it))
                .toDto()
        } ?: throw StradaNotFoundException("$userId is not found")

    @Transactional
    fun updatePaymentStatus(id: Long, paymentStatusUpdateRequest: PaymentStatusUpdateRequest) =
        load(id)
            .let {
                paymentRepository.save(Payment.of(
                    it.aid,
                    it.amount,
                    it.approvedAt,
                    it.cid,
                    it.createdAt,
                    it.itemName,
                    it.partnerOrderId,
                    it.partnerUserId,
                    it.paymentMethodType,
                    it.quantity,
                    it.tid,
                    paymentStatusUpdateRequest.paymentStatus,
                    it.user,
                    id
                ))
            }
            .toDto()

    @Transactional
    fun update(id: Long, paymentApproveRequest: PaymentApproveRequest) =
        load(id)
            .let {
                paymentRepository.save(paymentApproveRequest.toEntity(it.user, id))
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
