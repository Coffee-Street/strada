package com.wnsgml972.strada.api.v1.profile.service

import com.wnsgml972.strada.api.v1.profile.domain.UserProfile
import com.wnsgml972.strada.exception.StradaBadRequestException

fun UserProfile.toDto() = UserProfileDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.userId,
    this.point
)

fun UserProfileDTO.toEntity() = UserProfile.of(
    this.id,
    this.userId,
    this.point
)

fun UserProfileRequest.toEntity(id: Long? = null) = UserProfile.of(
    id,
    this.userId,
    this.point
)
