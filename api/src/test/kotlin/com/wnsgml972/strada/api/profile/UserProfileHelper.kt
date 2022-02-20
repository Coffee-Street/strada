package com.wnsgml972.strada.api.profile

import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.account.service.UserDto
import com.wnsgml972.strada.api.v1.account.service.UserService
import com.wnsgml972.strada.api.v1.account.service.toEntity
import com.wnsgml972.strada.api.v1.profile.service.UserProfileDTO
import com.wnsgml972.strada.api.v1.profile.service.UserProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserProfileHelper @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val userProfileService: UserProfileService,
) {
    fun signUp(userId: String): UserDto =
        userService.signUp(userId).userDto

    fun select(id: Long): UserProfileDTO =
        userProfileService.selectById(id)

    fun selectByUserId(userId: String): UserProfileDTO =
        userProfileService.selectByUserId(userId)

    fun signOut(userId: String) {
        val entity = userService.findById(userId).toEntity()
        userRepository.delete(entity)
    }
}