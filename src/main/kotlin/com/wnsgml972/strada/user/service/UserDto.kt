package com.wnsgml972.strada.user.service

data class UserDto(
    val id: Long,
    val username: String,
    val password: String,
    val isEnabled: Boolean,
    val userRole: UserRole
)
