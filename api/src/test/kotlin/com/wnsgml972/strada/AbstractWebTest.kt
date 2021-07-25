package com.wnsgml972.strada

import org.junit.jupiter.api.TestInstance
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractWebTest
