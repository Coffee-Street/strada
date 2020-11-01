package com.wnsgml972.strada.user.service

import com.wnsgml972.strada.user.domain.UserRepository
import com.wnsgml972.strada.user.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList

@Service
class UserService(
    private val userRepository: UserRepository
){

    fun findAll(): List<User> {
        val it = userRepository.findAll()
        val users = ArrayList<User>()

        it.forEach { e -> users.add(e) }

        return users
    }

    fun count(): Long {
        return userRepository.count()
    }

    fun deleteById(userId: Long) {
        userRepository.deleteById(userId)
    }
}