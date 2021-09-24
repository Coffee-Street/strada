package com.wnsgml972.strada.api.v1.profile.service

import com.wnsgml972.strada.api.v1.profile.domain.Profile
import com.wnsgml972.strada.exception.StradaBadRequestException

fun Profile.toDto() = ProfileDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.userId,
    this.point
)

fun ProfileDTO.toEntity() = Profile.of(
    this.id,
    this.userId,
    this.point
)

fun ProfileRequest.toDto(id: Long = 0) = ProfileDTO(
    id,
    this.userId,
    this.point
)
