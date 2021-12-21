package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.api.v1.profile.service.UserProfileRequest
import com.wnsgml972.strada.api.v1.profile.service.UserProfileService
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userProfileService: UserProfileService,
) {
    @Transactional(readOnly = true)
    fun findAll() = userRepository.findAll().map { it.toDto() }

    @Transactional(readOnly = true)
    fun findById(id: String): UserDto = load(id).toDto()

    @Transactional
    fun signUp(id: String, isEnabled: Boolean = true): UserDto {
        if (userRepository.existsById(id)) {
            userProfileService.insert(UserProfileRequest(id, INITIAL_POINT))
        }

        return userRepository.save(User.of(id, isEnabled)).toDto()
    }

    @Transactional
    fun signOut(id: String) {
        userProfileService.delete(id)
        load(id)
            .run {
                userRepository.delete(this)
            }
    }

    private fun load(id: String): User =
        userRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id Not Found") }
            .let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            }

    companion object : KLogging() {
        private const val INITIAL_POINT: Long = 0
    }
}
