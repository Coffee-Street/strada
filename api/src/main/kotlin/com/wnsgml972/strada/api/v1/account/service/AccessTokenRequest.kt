package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.base.AbstractValueObject
import javax.validation.constraints.Size

/**
 * JWT AccessToken 요청 DTO
 *
 * @property phoneNumber Jwt payload 에 들어갈 segment
 */
data class AccessTokenRequest(
    @field:Size(max = 14) val phoneNumber: String
) : AbstractValueObject() {

    override fun equalProperties(other: Any): Boolean {
        return other is AccessTokenRequest &&
                phoneNumber == other.phoneNumber
    }
}
