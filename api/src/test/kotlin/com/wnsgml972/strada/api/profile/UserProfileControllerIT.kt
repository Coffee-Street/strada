package com.wnsgml972.strada.api.profile

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.v1.profile.controller.UserProfileController
import com.wnsgml972.strada.api.v1.profile.service.UserProfileDTO
import com.wnsgml972.strada.api.v1.profile.service.UserProfileRequest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class UserProfileControllerIT @Autowired constructor(
    private val client: WebTestClient,
    private val authHelper: AuthHelper,
//    private val userHelper: UserHelper,
) : IntegrationTest() {

//    @BeforeEach
//    fun `유저 생성`() {
//
//    }

//    @Test
//    fun `결제 후 1000포인트 추가`() {
//
//        val userProfileRequest = UserProfileRequest(userId = "010-1234-1234", point = 1000)
//
//        val accessToken = authHelper.getAccessToken()
//        client.post()
//            .uri("${UserProfileController.USER_PROFILE_BASE_URL}/test")
//            .header("Authorization", "Bearer $accessToken")
//            .contentType(MediaType.APPLICATION_JSON)
//            .bodyValue(userProfileRequest)
//            .exchange()
//            .expectStatus()
//            .is2xxSuccessful
//            .expectBody<UserProfileDTO>()
//    }
}
