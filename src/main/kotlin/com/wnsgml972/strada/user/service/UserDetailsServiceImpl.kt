package com.wnsgml972.strada.user.service

import com.wnsgml972.strada.security.exception.UserNotFoundException
import com.wnsgml972.strada.user.domain.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetailsDto {
        return userRepository.findByUsername(username)
            .map {
                u -> UserDetailsDto(u.toDto(),
                    Collections.singleton(SimpleGrantedAuthority(u.userRole.toString())))
            }
            .orElseThrow {
                UserNotFoundException(username)
            }
    }
}
