package com.wnsgml972.strada.api.v1.account.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.account.controller.AccountController.Companion.ACCOUNT_BASE_URL
import com.wnsgml972.strada.api.v1.account.service.AccessTokenRequest
import com.wnsgml972.strada.api.v1.account.service.AccessTokenResponse
import com.wnsgml972.strada.api.v1.account.service.JwtService
import com.wnsgml972.strada.api.v1.account.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(path = [ACCOUNT_BASE_URL])
@Tag(
    name = "Account",
    description = """유저에게 토큰을 발급해주는 API"""
)
class AccountController @Autowired constructor(
    private val userService: UserService,
    private val jwtService: JwtService
) {

    @PostMapping
    @Operation(summary = "유저 회원가입 및 토큰 발급")
    @ApiResponse(responseCode = "200", description = "access token")
    fun signUp(
        @Parameter(
            name = "payload",
            description = "토큰 발급을 위한 phoneNumber를 입력해 주세요. 유효 시간은 60분입니다.",
            required = true,
            examples = [
                ExampleObject(name = "phoneNumber", value = "010-1111-1111"),
            ]
        )
        @Valid @RequestBody accessTokenRequest: AccessTokenRequest
    ): AccessTokenResponse {
        val phoneNumber = accessTokenRequest.phoneNumber
        userService.signUp(phoneNumber)
        return jwtService.createToken(phoneNumber)
    }

    companion object : KLogging() {
        private const val ACCOUNT_SERVICE_NAME = "account"
        const val ACCOUNT_BASE_URL = "$BASE_URL_V1/$ACCOUNT_SERVICE_NAME"
    }
}
