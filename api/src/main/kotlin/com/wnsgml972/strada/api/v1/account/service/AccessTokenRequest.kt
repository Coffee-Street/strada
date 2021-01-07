package com.wnsgml972.strada.api.v1.account.service

import com.fasterxml.jackson.annotation.JsonProperty
import com.wnsgml972.strada.api.base.AbstractValueObject
import javax.validation.constraints.Size

/**
 * JWT AccessToken 요청 DTO
 *
 * @property userId Jwt payload 에 들어갈 userId
 * @property segment Jwt payload 에 들어갈 segment
 */
data class AccessTokenRequest(
    val userId: Long,

    @field:Size(max = 14)
    @get:JsonProperty("phoneNumber")
    val segment: String
) : AbstractValueObject() {

    override fun equalProperties(other: Any): Boolean {
        return other is AccessTokenRequest &&
            userId == other.userId &&
            segment == other.segment
    }
}
