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

    @Test
    @Order(1)
    fun `payment ready test`() {
        val dto = KakaoRestApiReadyRequest(
            "TC0ONETIME",
            "partner_order_id",
            "partner_user_id",
            "초코파이",
            "1",
            "2000",
            "200",
            "0",
            "http://127.0.0.1:8080/strada/v1/payment/test/approve",
            "http://127.0.0.1:3000/fail",
            "http://127.0.0.1:3000/cancel",
        )
        val accessToken = authHelper.getAccessToken()
        client.post()
            .uri("${KakaoPaymentController.KAKAO_PAYMENT_BASE_URL}/ready")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dto)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody<PaymentDto>()
            .consumeWith { result -> PaymentControllerIT.logger.debug { "result=${result.responseBody}" } }
    }

    companion object : KLogging()



}