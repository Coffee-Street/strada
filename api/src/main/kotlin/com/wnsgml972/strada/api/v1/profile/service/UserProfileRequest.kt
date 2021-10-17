package com.wnsgml972.strada.api.v1.profile.service

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Positive

data class UserProfileRequest(
    @Length(min = 1)
    val userId: String,

    @Positive
    val point: Long,
)
