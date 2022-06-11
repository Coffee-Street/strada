package com.wnsgml972.strada.api.payment

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.profile.UserProfileHelper
import com.wnsgml972.strada.api.v1.payment.controller.KakaoPaymentController
import com.wnsgml972.strada.api.v1.payment.service.AmountVo
import com.wnsgml972.strada.api.v1.payment.service.KakaoRestApiReadyRequest
import com.wnsgml972.strada.api.v1.payment.service.PaymentDto
import com.wnsgml972.strada.api.v1.payment.service.PaymentApproveRequest
import mu.KLogging
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class PaymentControllerIT@Autowired constructor(
    private val client: WebTestClient,
    private val authHelper: AuthHelper,
    private val paymentHelper: PaymentHelper,
    private val userProfileHelper: UserProfileHelper,
) : IntegrationTest() {

    @BeforeEach
    fun `유저 생성`() {
        userProfileHelper.signUp(authHelper.phoneNumber)
        Thread.sleep(1000)
    }
    @AfterEach
    fun `clear dummy date after test`(){
        paymentHelper.clearPayment()
        userProfileHelper.signOut(authHelper.phoneNumber)
    }
    companion object : KLogging()
}