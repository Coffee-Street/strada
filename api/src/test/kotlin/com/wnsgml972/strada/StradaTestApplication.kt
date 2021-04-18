package com.wnsgml972.strada

import com.wnsgml972.strada.config.StradaDomainConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootApplication
@Import(StradaDomainConfig::class)
class StradaTestApplication { }
