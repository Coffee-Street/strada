package com.wnsgml972.strada.api.payment


import com.wnsgml972.strada.api.v1.payment.service.KakaoPaymentService
import com.wnsgml972.strada.api.v1.payment.service.PaymentApproveRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
class PaymentHelper  @Autowired constructor(
    private val kakaoPaymentService: KakaoPaymentService
){

    @Transactional
    fun clearPayment() =
        kakaoPaymentService
            .selectAll()
            .forEach{
                it.tid?.let { it1 ->
                    kakaoPaymentService
                        .deleteByTid(it1)
                }
            }
}