package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.kakao.service.KakaoApiService
import com.wnsgml972.strada.api.v1.payment.domain.Payment
import com.wnsgml972.strada.api.v1.payment.domain.PaymentRepository
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import mu.KLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@SuppressWarnings("TooManyFunctions")
class KakaoPaymentService(
    private val paymentRepository: PaymentRepository,
    private val userRepository: UserRepository,
    private val kakaoApiService: KakaoApiService

) {

    fun approvePayment(
        paymentApproveRequest: PaymentApproveRequest,
    ): KakaoRestApiApproveResponse =
        paymentRepository.findByTid(paymentApproveRequest.tid)?.let { payment ->
            payment.id?.runCatching {
                kakaoApiService.approvePayment(KakaoRestApiApproveRequest(
                    payment.cid,
                    paymentApproveRequest.tid,
                    payment.partnerOrderId,
                    payment.partnerUserId,
                    paymentApproveRequest.pg_token))
            }?.onSuccess { response ->
                update(payment.id!!, response)
            }?.onFailure {
                updatePaymentStatus(payment.id!!, PaymentStatus.FAILED)
                throw StradaNotFoundException("${payment.tid} is failed")
            }?.getOrNull()
        } ?: throw StradaNotFoundException("${paymentApproveRequest.tid} is not found")

    fun readyPayment(
        targetUserId: String,
        kakaoRestApiReadyRequest: KakaoRestApiReadyRequest,
    ): KakaoRestApiReadyResponse =
        userRepository.findByIdOrNull(targetUserId)
            ?.let { user ->
                paymentRepository
                    .save(kakaoRestApiReadyRequest.toEntity(user))
            }?.let { payment ->
                payment.id?.runCatching {
                    kakaoApiService.readyPayment(targetUserId, kakaoRestApiReadyRequest)
                }?.onSuccess { response ->
                    update(payment.id!!, response)
                }?.onFailure {
                    updatePaymentStatus(payment.id!!, PaymentStatus.FAILED)
                    throw StradaNotFoundException("${payment.tid} is failed")
                }?.getOrNull()
            } ?: throw StradaNotFoundException("$targetUserId is not found")

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
    fun insert(userId: String, kakaoRestApiReadyRequest: KakaoRestApiReadyRequest) =
        userRepository.findByIdOrNull(userId)
            ?.let {
                paymentRepository
                    .save(kakaoRestApiReadyRequest.toEntity(it))
                    .toDto()
            } ?: throw StradaNotFoundException("$userId is not found")

    @Transactional
    fun update(id: Long, kakaoRestApiReadyResponse: KakaoRestApiReadyResponse): PaymentDto =
        load(id)
            .let {
                paymentRepository.save(Payment.of(
                    it.aid,
                    it.amount,
                    it.approvedAt,
                    it.cid,
                    it.itemName,
                    it.partnerOrderId,
                    it.partnerUserId,
                    it.paymentMethodType,
                    it.quantity,
                    kakaoRestApiReadyResponse.tid,
                    PaymentStatus.READY,
                    it.user,
                    it.id
                )).toDto()
            }

    @Transactional
    fun update(id: Long, kakaoRestApiApproveResponse: KakaoRestApiApproveResponse): PaymentDto =
        load(id)
            .let {
                paymentRepository.save(Payment.of(
                    kakaoRestApiApproveResponse.aid,
                    kakaoRestApiApproveResponse.amount,
                    kakaoRestApiApproveResponse.approved_at,
                    kakaoRestApiApproveResponse.cid,
                    kakaoRestApiApproveResponse.item_name,
                    kakaoRestApiApproveResponse.partner_order_id,
                    kakaoRestApiApproveResponse.partner_user_id,
                    kakaoRestApiApproveResponse.payment_method_type,
                    kakaoRestApiApproveResponse.quantity.toInt(),
                    kakaoRestApiApproveResponse.tid,
                    PaymentStatus.APPROVED,
                    it.user,
                    it.id
                )).toDto()
            }

    @Transactional
    fun updatePaymentStatus(id: Long, paymentStatus: PaymentStatus) =
        load(id)
            .let {
                paymentRepository.save(Payment.of(
                    it.aid,
                    it.amount,
                    it.approvedAt,
                    it.cid,
                    it.itemName,
                    it.partnerOrderId,
                    it.partnerUserId,
                    it.paymentMethodType,
                    it.quantity,
                    it.tid,
                    paymentStatus,
                    it.user,
                    id
                ))
            }
            .toDto()

    @Transactional
    fun delete(id: Long) =
        load(id)
            .let {
                paymentRepository.delete(it)
            }

    @Transactional
    fun deleteByTid(tid: String) =
        paymentRepository.findByTid(tid)
            ?.let {
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
