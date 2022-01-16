package com.wnsgml972.strada.api.v1.profile.service

import javax.validation.constraints.Min

data class UserProfileRequest(
    val userId: String,
    @field:Min(0) val point: Long,
)
