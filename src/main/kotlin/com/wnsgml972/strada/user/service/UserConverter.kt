package com.wnsgml972.strada.user.service

import com.wnsgml972.strada.user.domain.User

fun User.toDto() = UserDto(
    this.id,
    this.username,
    this.password,
    this.isEnabled,
    this.userRole
)