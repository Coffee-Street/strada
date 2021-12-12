package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.api.v1.profile.service.UserProfileRequest
import com.wnsgml972.strada.api.v1.profile.service.UserProfileService
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userProfileService: UserProfileService,
) {
    @Transactional(readOnly = true)
    fun findAll() = userRepository.findAll().map { it.toDto() }

    @Transactional
    fun signUp(id: String, isEnabled: Boolean = true): UserDto {
        load(id)
            ?: userProfileService.insert(UserProfileRequest(id, 0))

        return userRepository.save(User.of(id, isEnabled)).toDto()
    }

    @Transactional(readOnly = true)
    fun findById(id: String): UserDto {
        return userRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id Not Found") }
            .toDto()
    }

    @Transactional
    private fun load(id: String): User? =
        userRepository
            .findById(id)
            .orElse(null)
            .let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            }
}
