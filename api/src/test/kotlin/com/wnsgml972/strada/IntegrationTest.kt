package com.wnsgml972.strada

import com.wnsgml972.strada.config.StradaDomainConfig
import com.wnsgml972.strada.config.StradaWebConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(classes = [StradaTestApplication::class], webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(
    StradaDomainConfig::class,
    StradaWebConfig::class,
)
class IntegrationTest : AbstractWebTest()
