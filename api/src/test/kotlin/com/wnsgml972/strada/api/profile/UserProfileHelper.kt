package com.wnsgml972.strada.api.profile

import com.wnsgml972.strada.api.v1.account.service.UserDto
import com.wnsgml972.strada.api.v1.account.service.UserService
import com.wnsgml972.strada.api.v1.profile.service.UserProfileDTO
import com.wnsgml972.strada.api.v1.profile.service.UserProfileRequest
import com.wnsgml972.strada.api.v1.profile.service.UserProfileService
import com.wnsgml972.strada.exception.StradaIllegalStateException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserProfileHelper @Autowired constructor(
    private val userService: UserService,
    private val userProfileService: UserProfileService,
) {
    fun signup(userId: String): UserDto =
        userService.signUp(userId)

    fun select(id: Long): UserProfileDTO =
        userProfileService.selectById(id)

    fun selectByUserId(userId: String): UserProfileDTO =
        userProfileService.selectByUserId(userId)
}