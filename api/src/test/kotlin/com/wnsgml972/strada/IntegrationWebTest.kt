package com.wnsgml972.strada

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(classes = [StradaTestApplication::class], webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationWebTest : AbstractWebTest()
