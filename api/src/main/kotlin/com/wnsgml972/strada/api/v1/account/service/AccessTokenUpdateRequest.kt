package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.base.AbstractValueObject
import javax.validation.constraints.Size

class AccssTokenUpdateRequest (
    @field:Size(max = 14) val phoneNumber: String,
    val isEnabled: Boolean,
) : AbstractValueObject() {

    override fun equalProperties(other: Any): Boolean {
        return other is AccessTokenRequest &&
                phoneNumber == other.phoneNumber
    }
}