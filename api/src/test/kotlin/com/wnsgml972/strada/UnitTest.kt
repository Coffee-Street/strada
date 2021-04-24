package com.wnsgml972.strada

import com.wnsgml972.strada.config.StradaWebConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(classes = [StradaTestApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(
    StradaWebConfig::class,
)
class UnitTest : AbstractWebTest()
