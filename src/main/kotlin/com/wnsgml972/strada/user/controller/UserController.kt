package com.wnsgml972.strada.user.controller

import com.wnsgml972.strada.user.domain.User
import com.wnsgml972.strada.user.service.UserRole
import com.wnsgml972.strada.user.service.UserService
import com.wnsgml972.strada.user.service.UserSignUpOrLoginRequest
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private var passwordEncoder: BCryptPasswordEncoder,
    private var userService: UserService
) {
    companion object : KLogging()

    @PostMapping
    fun signUp(
        @RequestParam(value = "username", required = true) userSignUpOrLoginRequest: UserSignUpOrLoginRequest
    ): ResponseEntity<*>? {
        if (!userService.findByUsername(userSignUpOrLoginRequest.username).isPresent) {
            val user = User.of(userSignUpOrLoginRequest.username, userSignUpOrLoginRequest.username, UserRole.USER)
            val userDto = userService.signUp(user)
            logger.debug("Sign Up Complete $userDto")
        }

        return ResponseEntity.ok().build<Any>()
    }

    @GetMapping
    fun findAll(): ResponseEntity<*>? {
        return ResponseEntity.ok(userService.findAll())
    }
}