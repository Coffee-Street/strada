package com.wnsgml972.strada.user.controller

import com.wnsgml972.strada.user.service.UserService
import com.wnsgml972.strada.user.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private var userService: UserService
) {

    @GetMapping("/users")
    fun allUsers(): List<User> {
        return userService.findAll()
    }

    @GetMapping("/users/count")
    fun count(): Long {
        return userService.count()
    }

    @DeleteMapping("/users/{id}")
    fun delete(@PathVariable id: String) {
        val userId = id.toLong()
        userService.deleteById(userId)
    }
}