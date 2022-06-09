package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.account.service.UserService
import com.wnsgml972.strada.api.v1.account.service.toEntity
import com.wnsgml972.strada.api.v1.kakao.service.KakaoApiService
import com.wnsgml972.strada.api.v1.payment.domain.Payment
import com.wnsgml972.strada.api.v1.payment.domain.PaymentRepository
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@SuppressWarnings("TooManyFunctions")
class KakaoPaymentService(
    private val paymentRepository: PaymentRepository,
    private val kakaoApiService: KakaoApiService,
    private val userService: UserService

) {

    fun approvePayment(
        paymentApproveRequest: PaymentApproveRequest,
    ): KakaoRestApiApproveResponse =
        load(paymentApproveRequest.tid).runCatching {
            kakaoApiService.approvePayment(KakaoRestApiApproveRequest(
                this.cid,
                paymentApproveRequest.tid,
                this.partnerOrderId,
                this.partnerUserId,
                paymentApproveRequest.pgToken))
        }.onSuccess { response ->
            update(response)
        }.onFailure {
            updatePaymentStatus(paymentApproveRequest.tid, PaymentStatus.FAILED)
        }.getOrThrow()

    fun readyPayment(
        targetUserId: String,
        kakaoRestApiReadyRequest: KakaoRestApiReadyRequest,
    ): KakaoRestApiReadyResponse =
        userService.findById(targetUserId)
            .let { user ->
                paymentRepository
                    .save(kakaoRestApiReadyRequest.toEntity(user.toEntity()))
            }.let { payment ->
                runCatching {
                    kakaoApiService.readyPayment(targetUserId, kakaoRestApiReadyRequest)
                }.onSuccess { response ->
                    payment.id?.let { id ->
                        update(id, response)
                    }
                }.onFailure {
                    payment.tid ?: throw StradaNotFoundException("${payment.id} is failed. $it", it)
                    updatePaymentStatus(payment.tid, PaymentStatus.FAILED)
                }.getOrThrow()
            }

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
        userService.findById(userId)
            .let {
                paymentRepository
                    .save(kakaoRestApiReadyRequest.toEntity(it.toEntity()))
                    .toDto()
            }

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
    fun update(kakaoRestApiApproveResponse: KakaoRestApiApproveResponse): PaymentDto =
        load(kakaoRestApiApproveResponse.tid)
            .let {
                paymentRepository
                    .save(kakaoRestApiApproveResponse.toEntity(it.user, it.id))
                    .toDto()
            }

    @Transactional
    fun updatePaymentStatus(tid: String, paymentStatus: PaymentStatus) =
        load(tid)
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
                    it.id
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
            .orElseThrow { StradaNotFoundException("$tid Not Found") }
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

    @Transactional(readOnly = true)
    fun load(tid: String): Payment =
        paymentRepository
            .findByTid(tid)
            .orElseThrow { StradaNotFoundException("$tid Not Found") }
            .let {
                it.id ?: throw StradaIllegalStateException("id of $tid is not initialized")
                it
            }

    companion object : KLogging()
}
