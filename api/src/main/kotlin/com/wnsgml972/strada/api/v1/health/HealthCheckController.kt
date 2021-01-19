package com.wnsgml972.strada.api.v1.health

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.health.HealthCheckController.Companion.HEALTH_BASE_URL
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "Health Check",
    description = "Azar Promotion의 Health Check API 입니다."
)
@RestController
@RequestMapping(path = [HEALTH_BASE_URL])
class HealthCheckController @Autowired constructor(
    val userRepository: UserRepository
) {

    @GetMapping
    fun healthCheck() = readHealthCheck()

    @GetMapping("/readness")
    fun readHealthCheck(): ResponseEntity<Nothing> {
        if (userRepository.count() >= 0) {
            return ResponseEntity(HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE)
    }

    @GetMapping("/liveness")
    @ResponseBody
    fun liveHealthCheck() = ResponseEntity<Nothing>(HttpStatus.OK)

    companion object : KLogging() {
        private const val HEALTH_SERVICE_NAME = "health"
        const val HEALTH_BASE_URL = "$BASE_URL_V1/$HEALTH_SERVICE_NAME"
    }
}
