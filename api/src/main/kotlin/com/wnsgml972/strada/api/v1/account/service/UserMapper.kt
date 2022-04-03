package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.exception.StradaIllegalStateException

fun User.toDto() = UserDto(
    this.id,
    this.isEnabled,
)

fun UserDto.toEntity() = User.of(
    this.id ?: throw StradaIllegalStateException("user의 id($id)는 null 일 수 없습니다."),
    this.isEnabled
)
