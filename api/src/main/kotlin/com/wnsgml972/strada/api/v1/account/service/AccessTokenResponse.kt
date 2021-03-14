package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.base.AbstractValueObject

/**
 * JWT AccessToken 응답 DTO
 *
 * @property accessToken 완성된 Jwt accessToken 토큰
 */
data class AccessTokenResponse(
    val accessToken: String
) : AbstractValueObject() {

    override fun equalProperties(other: Any): Boolean {
        return other is AccessTokenResponse &&
            accessToken == other.accessToken
    }
}
