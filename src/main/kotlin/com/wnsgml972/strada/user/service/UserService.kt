package com.wnsgml972.strada.user.service

import com.wnsgml972.strada.user.domain.UserRepository
import com.wnsgml972.strada.user.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.ArrayList

@Service
class UserService(
    private val userRepository: UserRepository
){
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
    fun findByUsername(username: String)
        = userRepository.findByUsername(username)
}