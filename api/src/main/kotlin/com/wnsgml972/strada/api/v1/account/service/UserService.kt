package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.account.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.ArrayList

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun findAll(): List<User> {
        val it = userRepository.findAll()
        val users = ArrayList<User>()

        it.forEach { e -> users.add(e) }

        return users
    }

    @Transactional
    fun signUp(user: User): UserDto {
        return userRepository.save(user).toDto()
    }

    @Transactional(readOnly = true)
    fun findByUsername(username: String) =
        userRepository.findByUsername(username)
}
