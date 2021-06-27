package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun findAll() = userRepository.findAll().map { it.toDto() }

    @Transactional
    fun signUp(id: String, isEnabled: Boolean = true) = userRepository.save(User.of(id, isEnabled)).toDto()

    @Transactional
    fun signUpdate(id: String, isEnabled: Boolean) = userRepository.save(User.of(id, isEnabled)).toDto()

    @Transactional(readOnly = true)
    fun findById(id: String): UserDto {
        return userRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id Not Found") }
            .toDto()
    }
}
