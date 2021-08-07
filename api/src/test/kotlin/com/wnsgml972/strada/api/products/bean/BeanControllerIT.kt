//package com.wnsgml972.strada.api.products.bean
//
//import com.wnsgml972.strada.AuthHelper
//import com.wnsgml972.strada.IntegrationTest
//import com.wnsgml972.strada.api.products.ProductHelper
//import com.wnsgml972.strada.api.products.coffee.CoffeeControllerIT
//import com.wnsgml972.strada.api.v1.product.bean.controller.admin.BeanController
//import com.wnsgml972.strada.api.v1.product.bean.service.BeanDTO
//import com.wnsgml972.strada.api.v1.product.bean.service.BeanInsertRequest
//import com.wnsgml972.strada.api.v1.product.coffee.controller.admin.CoffeeController
//import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeDTO
//import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeInsertRequest
//import mu.KLogging
//import org.amshove.kluent.shouldBeEqualTo
//import org.junit.jupiter.api.*
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.MediaType
//import org.springframework.test.web.reactive.server.WebTestClient
//import org.springframework.test.web.reactive.server.expectBody
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
//class BeanControllerIT@Autowired constructor(
//    private val client: WebTestClient,
//    private val authHelper: AuthHelper,
//    private val productHelper: ProductHelper
//) : IntegrationTest(){
//
//    @BeforeEach
//    fun `insert dummy Bean before each test`() {
//        val beanDTO = BeanDTO(
//            "dummy",
//            "test",
//            "test",
//            "test",
//            "test",
//            "test",
//            "test",
//            "test",
//        )
//
//        productHelper.insertBean(beanDTO)
//    }
//
//    @AfterEach
//    fun `delete after each test`(){
//        productHelper.deleteBean("dummy")
//    }
//
//
//    @Test
//    @Order(1)
//    fun `insert Bean using post to BeanController`() {
//        val beanInsertRequest = BeanInsertRequest(
//            "test2",
//            "test2",
//            "test2",
//            "test2",
//            "test2",
//            "test2",
//            "test2",
//        )
//        val accessToken = authHelper.getAccessToken()
//        client.post()
//            .uri("${BeanController.BEAN_BASE_URL}/test_bean")
//            .header("Authorization", "Bearer $accessToken")
//            .contentType(MediaType.APPLICATION_JSON)
//            .bodyValue(beanInsertRequest)
//            .exchange()
//            .expectStatus().is2xxSuccessful
//            .expectBody<BeanDTO>()
//            .consumeWith { result -> BeanControllerIT.logger.debug { "result=${result.responseBody}" } }
//
//        productHelper.deleteBean("test_bean")
//    }
//
//    @Test
//    @Order(2)
//    fun `select Bean using get from BeanController`() {
//        val accessToken = authHelper.getAccessToken()
//
//        client.get()
//            .uri("${BeanController.BEAN_BASE_URL}/dummy")
//            .header("Authorization", "Bearer $accessToken")
//            .exchange()
//            .expectStatus().is2xxSuccessful
//            .expectBody<BeanDTO>()
//            .consumeWith { result -> BeanControllerIT.logger.debug { "result=${result.responseBody}" } }
//    }
//
//    @Test
//    @Order(3)
//    fun `select all Bean using get from BeanController`() {
//        val accessToken = authHelper.getAccessToken()
//        client.get()
//            .uri("${BeanController.BEAN_BASE_URL}")
//            .header("Authorization", "Bearer $accessToken")
//            .exchange()
//            .expectStatus().is2xxSuccessful
//            .expectBody<List<BeanDTO>>()
//            .consumeWith { result ->
//                result.responseBody?.forEach { it -> BeanControllerIT.logger.debug { "result=${it}" } }
//            }
//    }
//
//    @Test
//    @Order(4)
//    fun `update Bean using put to BeanController`() {
//        val beanInsertRequest = BeanInsertRequest(
//            "update",
//            "update",
//            "update",
//            "update",
//            "update",
//            "test2",
//            "test2",
//        )
//        val accessToken = authHelper.getAccessToken()
//        client.put()
//            .uri("${BeanController.BEAN_BASE_URL}/dummy")
//            .header("Authorization", "Bearer $accessToken")
//            .contentType(MediaType.APPLICATION_JSON)
//            .bodyValue(beanInsertRequest)
//            .exchange()
//            .expectStatus().is2xxSuccessful
//            .expectBody<BeanDTO>().consumeWith { result ->
//                result.responseBody?.origin shouldBeEqualTo "update"
//                BeanControllerIT.logger.debug { "result=${result.responseBody}" }
//            }
//
//    }
//
//    @Test
//    @Order(5)
//    fun `delete Bean using delete from BeanController`() {
//
//        val beanDTO = BeanDTO(
//            "test_bean",
//            "test",
//            "test",
//            "test",
//            "test",
//            "test",
//            "test",
//            "test",
//        )
//        productHelper.insertBean(beanDTO)
//
//        val accessToken = authHelper.getAccessToken()
//        client.delete()
//            .uri("${BeanController.BEAN_BASE_URL}/test_bean")
//            .header("Authorization", "Bearer $accessToken")
//            .exchange()
//            .expectStatus().is2xxSuccessful
//    }
//
//    @Test
//    @Order(6)
//    fun `delete Non Exist Bean using delete from BeanController`() {
//        val accessToken = authHelper.getAccessToken()
//        client.delete()
//            .uri("${BeanController.BEAN_BASE_URL}/noting")
//            .header("Authorization", "Bearer $accessToken")
//            .exchange()
//            .expectStatus().isNotFound()
//    }
//
//
//    companion object : KLogging()
//}