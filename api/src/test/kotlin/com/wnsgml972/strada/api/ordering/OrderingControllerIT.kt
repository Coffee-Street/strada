package com.wnsgml972.strada.api.ordering

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.v1.ordering.controller.OrderingController
import com.wnsgml972.strada.api.v1.ordering.service.OrderingDetailDTO
import com.wnsgml972.strada.api.v1.ordering.service.OrderingRequest
import com.wnsgml972.strada.api.v1.ordering.service.OrderingResponse
import com.wnsgml972.strada.api.v1.ordering.service.OrderingStatus
import mu.KLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class OrderingControllerIT @Autowired constructor(
    private val client: WebTestClient,
) : IntegrationTest() {

    val authHelper = AuthHelper(client)

    @Test
    fun `create ordering with ice americano`() {
        val accessToken = authHelper.getAccessToken()
        val orderingRequest = OrderingRequest(OrderingStatus.REQUEST, ArrayList<OrderingDetailDTO>())

        client.post()
            .uri(OrderingController.ORDERING_BASE_URL)
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(orderingRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<OrderingResponse>()
    }

//    @Test
//    fun `update ordering finish`() {
//
//    }

//    @Test
//    fun `update ordering cancel`() {
//
//    }

    companion object : KLogging()
}