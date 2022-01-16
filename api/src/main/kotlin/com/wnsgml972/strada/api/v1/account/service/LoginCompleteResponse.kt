package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.base.AbstractValueObject
import javax.validation.constraints.NotBlank

/**
 * LoginCompleteResponse 응답 DTO
 *
 * @property userDto 완성된 UserDto
 * @property accessToken 완성된 Jwt accessToken 토큰
 */
data class LoginCompleteResponse(
    val userDto: UserDto,
    @field:NotBlank val accessToken: String
) : AbstractValueObject() {

    override fun equalProperties(other: Any): Boolean {
        return other is LoginCompleteResponse &&
                userDto == other.userDto &&
                accessToken == other.accessToken
    }
}
