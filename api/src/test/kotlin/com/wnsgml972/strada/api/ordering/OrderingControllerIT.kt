package com.wnsgml972.strada.api.ordering

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.products.ProductHelper
import com.wnsgml972.strada.api.v1.option.bean.service.BeanOptionRequest
import com.wnsgml972.strada.api.v1.option.bean.service.GrindType
import com.wnsgml972.strada.api.v1.option.bread.service.BreadOptionRequest
import com.wnsgml972.strada.api.v1.option.bread.service.HereOrToGo
import com.wnsgml972.strada.api.v1.option.drink.service.CupType
import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionRequest
import com.wnsgml972.strada.api.v1.option.drink.service.HotOrIcedType
import com.wnsgml972.strada.api.v1.option.drink.service.QuantityType
import com.wnsgml972.strada.api.v1.option.drizzle.service.DrizzleOptionRequest
import com.wnsgml972.strada.api.v1.option.syrup.service.SyrupOptionRequest
import com.wnsgml972.strada.api.v1.ordering.controller.OrderingController
import com.wnsgml972.strada.api.v1.ordering.service.OrderingDTO
import com.wnsgml972.strada.api.v1.ordering.service.OrderingDetailRequest
import com.wnsgml972.strada.api.v1.ordering.service.OrderingRequest
import com.wnsgml972.strada.api.v1.ordering.service.OrderingStatus
import com.wnsgml972.strada.api.v1.product.bean.service.BeanDTO
import com.wnsgml972.strada.api.v1.product.bread.service.BreadDTO
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeDTO
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeInsertRequest
import mu.KLogging
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class OrderingControllerIT @Autowired constructor(
    private val client: WebTestClient,
    private val authHelper: AuthHelper,
    private val productHelper: ProductHelper,
) : IntegrationTest() {

    @Test
    fun `아이스 아메리카노 5샷 벤티 사이즈 재사용 컵으로 한잔이요`() {
        val beanDTO = productHelper.insertBean(
            BeanDTO(
            "dummy_bean",
            "dummy",
            "bean",
            "",
            "",
            "",
            "",
            ""
            )
        )

        val coffeeDTO = productHelper.insertCoffee(
            "americano",
            CoffeeInsertRequest(
                "",
                4400,
                "",
                "",
                listOf<BeanDTO>(beanDTO)
            )
        )

        val accessToken = authHelper.getAccessToken()
        val orderingRequest = OrderingRequest(OrderingStatus.REQUEST, listOf<OrderingDetailRequest>(
            OrderingDetailRequest(
                "americano",
                null,
                null,
                null,
                DrinkOptionRequest(
                    HotOrIcedType.ICED,
                    CupType.MULTI_USE,
                    591,
                    QuantityType.DEFAULT,
                    QuantityType.NONE,
                    QuantityType.DEFAULT,
                    QuantityType.NONE,
                    QuantityType.NONE,
                    "very thick",
                    5,
                    listOf<SyrupOptionRequest>(),
                    listOf<DrizzleOptionRequest>(),
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
            .expectBody<OrderingDTO>()
    }

    @Test
    fun `크로크무슈 포크 2개넣어서 포장이요`() {
        val breadDTO = productHelper.insertBread(BreadDTO(
            "Croque_Monsieur",
            "",
            5600,
            "",
            ""
        ))

        val accessToken = authHelper.getAccessToken()
        val orderingRequest = OrderingRequest(OrderingStatus.REQUEST, listOf<OrderingDetailRequest>(
            OrderingDetailRequest(
                null,
                null,
                "Croque_Monsieur",
                null,
                null,
                BreadOptionRequest(
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
        val beanDTO = productHelper.insertBean(
            BeanDTO(
                "Indonesian_Mandeling",
                "Indo",
                "nesian",
                "",
                "",
                "",
                "",
                ""
            )
        )

        val accessToken = authHelper.getAccessToken()
        val orderingRequest = OrderingRequest(OrderingStatus.REQUEST, listOf<OrderingDetailRequest>(
            OrderingDetailRequest(
                null,
                null,
                null,
                "Indonesian_Mandeling",
                null,
                null,
                BeanOptionRequest(
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