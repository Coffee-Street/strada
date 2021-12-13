package com.wnsgml972.strada.api.profile

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
    @Transactional
    fun signup(userId: String) {
        userService.signUp(userId)
    }

    @Transactional
    fun select(id: Long): UserProfileDTO =
        userProfileService.selectById(id)

    @Transactional
    fun selectByUserId(userId: String): UserProfileDTO =
        userProfileService.selectByUserId(userId)
}