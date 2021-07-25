package com.wnsgml972.strada.api.products.bean

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.reactive.server.WebTestClient

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class BeanControllerIT@Autowired constructor(
    private val client: WebTestClient,
    private val authHelper: AuthHelper,
) : IntegrationTest(){


}