package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.v1.account.domain.User

fun User.toDto() = UserDto(
    this.id,
    this.username,
    this.isEnabled,
)
