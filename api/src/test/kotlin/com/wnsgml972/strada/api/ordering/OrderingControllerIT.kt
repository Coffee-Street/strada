package com.wnsgml972.strada.api.ordering

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.v1.option.bean.service.BeanOptionDTO
import com.wnsgml972.strada.api.v1.option.bean.service.GrindType
import com.wnsgml972.strada.api.v1.option.bread.service.BreadOptionDTO
import com.wnsgml972.strada.api.v1.option.bread.service.HereOrToGo
import com.wnsgml972.strada.api.v1.option.drink.service.CreamType
import com.wnsgml972.strada.api.v1.option.drink.service.CupSizeType
import com.wnsgml972.strada.api.v1.option.drink.service.CupType
import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionDTO
import com.wnsgml972.strada.api.v1.option.drink.service.HotOnlyType
import com.wnsgml972.strada.api.v1.option.drink.service.HotOrIcedType
import com.wnsgml972.strada.api.v1.option.drink.service.IcedOnlyType
import com.wnsgml972.strada.api.v1.option.drink.service.MemoType
import com.wnsgml972.strada.api.v1.option.drink.service.MilkType
import com.wnsgml972.strada.api.v1.option.drink.service.WaterType
import com.wnsgml972.strada.api.v1.option.drizzle.service.DrizzleOptionDTO
import com.wnsgml972.strada.api.v1.option.syrup.service.SyrupOptionDTO
import com.wnsgml972.strada.api.v1.ordering.controller.OrderingController
import com.wnsgml972.strada.api.v1.ordering.service.OrderingDetailRequest
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
    fun `아이스 아메리카노 5샷 벤티 사이즈 재사용 컵으로 한잔이요`() {
        val accessToken = authHelper.getAccessToken()
        val orderingRequest = OrderingRequest(OrderingStatus.REQUEST, listOf<OrderingDetailRequest>(
            OrderingDetailRequest(
                DrinkOptionDTO(
                    0,
                    HotOrIcedType.ICED,
                    CupType.MULTI_USE,
                    CupSizeType.VENTI,
                    WaterType.DEFAULT,
                    MilkType.NONE,
                    IcedOnlyType.DEFAULT,
                    HotOnlyType.NONE,
                    CreamType.NONE,
                    MemoType.DEFAULT,
                    "진하게 타주세요",
                    5,
                    listOf<SyrupOptionDTO>(),
                    listOf<DrizzleOptionDTO>()
                ),
                null,
                null
            )
        ))

        client.post()
            .uri(OrderingController.ORDERING_BASE_URL)
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(orderingRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<OrderingResponse>()
    }

    @Test
    fun `크로크무슈 포크 2개넣어서 포장이요`() {
        val accessToken = authHelper.getAccessToken()
        val orderingRequest = OrderingRequest(OrderingStatus.REQUEST, listOf<OrderingDetailRequest>(
            OrderingDetailRequest(
                null,
                BreadOptionDTO(
                    0,
                    HereOrToGo.TOGO,
                    2
                ),
                null
            )
        ))

        client.post()
            .uri(OrderingController.ORDERING_BASE_URL)
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(orderingRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<OrderingResponse>()
    }

    @Test
    fun `인도네시아 만델링 원두 드립으로 내려먹을 1팩이요`() {
        val accessToken = authHelper.getAccessToken()
        val orderingRequest = OrderingRequest(OrderingStatus.REQUEST, listOf<OrderingDetailRequest>(
            OrderingDetailRequest(
                null,
                null,
                BeanOptionDTO(
                    0,
                    GrindType.DRIP
                )
            )
        ))

        client.post()
            .uri(OrderingController.ORDERING_BASE_URL)
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(orderingRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<OrderingResponse>()
    }

    companion object : KLogging()
}