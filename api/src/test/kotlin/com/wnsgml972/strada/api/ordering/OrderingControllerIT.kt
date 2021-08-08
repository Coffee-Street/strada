package com.wnsgml972.strada.api.ordering

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.v1.item.bread.service.BreadDTO
import com.wnsgml972.strada.api.v1.item.bread.service.BreadInsertRequest
import com.wnsgml972.strada.api.v1.item.coffee.service.BeanDTO
import com.wnsgml972.strada.api.v1.item.coffee.service.CoffeeDTO
import com.wnsgml972.strada.api.v1.item.coffee.service.CoffeeInsertRequest
import com.wnsgml972.strada.api.v1.option.bean.service.BeanOptionDTO
import com.wnsgml972.strada.api.v1.option.bean.service.GrindType
import com.wnsgml972.strada.api.v1.option.bread.service.BreadOptionDTO
import com.wnsgml972.strada.api.v1.option.bread.service.HereOrToGo
import com.wnsgml972.strada.api.v1.option.drink.service.CupType
import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionDTO
import com.wnsgml972.strada.api.v1.option.drink.service.HotOrIcedType
import com.wnsgml972.strada.api.v1.option.drizzle.service.DrizzleOptionDTO
import com.wnsgml972.strada.api.v1.option.syrup.service.SyrupOptionDTO
import com.wnsgml972.strada.api.v1.ordering.controller.OrderingController
import com.wnsgml972.strada.api.v1.ordering.service.OrderingDTO
import com.wnsgml972.strada.api.v1.ordering.service.OrderingDetailRequest
import com.wnsgml972.strada.api.v1.ordering.service.OrderingRequest
import com.wnsgml972.strada.api.v1.ordering.service.OrderingStatus
import mu.KLogging
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class OrderingControllerIT @Autowired constructor(
    private val client: WebTestClient,
    private val authHelper: AuthHelper,
) : IntegrationTest() {

    @Test
    fun `아이스 아메리카노 5샷 벤티 사이즈 재사용 컵으로 한잔이요`() {
        val accessToken = authHelper.getAccessToken()
        val orderingRequest =
            OrderingRequest(
                OrderingStatus.REQUEST,
                listOf<OrderingDetailRequest>(
                    OrderingDetailRequest(
                        CoffeeDTO(
                            "",
                            CoffeeInsertRequest(
                                "",
                                4400,
                                "",
                                "",
                                listOf<BeanDTO>()
                            ),
                        ),
                        null,
                        null,
                        null,
                        DrinkOptionDTO(
                            0,
                            HotOrIcedType.ICED,
                            CupType.MULTI_USE,
                            591,
                            3,
                            0,
                            1,
                            0,
                            0,
                            "진하게 타주세요",
                            5,
                            listOf<SyrupOptionDTO>(),
                            listOf<DrizzleOptionDTO>()
                        ),
                        null,
                        null
                    )
                )
            )

        client.post()
            .uri(OrderingController.ORDERING_BASE_URL)
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(orderingRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<OrderingDTO>()
    }

    @Test
    fun `크로크무슈 포크 2개넣어서 포장이요`() {
        val accessToken = authHelper.getAccessToken()
        val orderingRequest = OrderingRequest(OrderingStatus.REQUEST, listOf<OrderingDetailRequest>(
            OrderingDetailRequest(
                null,
                null,
                BreadDTO(
                    "",
                    BreadInsertRequest(
                        "",
                        5600,
                        "",
                        ""
                    ),
                ),
                null,
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
            .expectBody<OrderingDTO>()
    }

    @Test
    fun `인도네시아 만델링 원두 드립으로 내려먹을 1팩이요`() {
        val accessToken = authHelper.getAccessToken()
        val orderingRequest = OrderingRequest(OrderingStatus.REQUEST, listOf<OrderingDetailRequest>(
            OrderingDetailRequest(
                null,
                null,
                null,
                BeanDTO(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                ),
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
            .expectBody<OrderingDTO>()
    }

    companion object : KLogging()
}