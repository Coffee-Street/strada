package com.wnsgml972.strada.user.service

import com.wnsgml972.strada.security.exception.UserNotFoundException
import com.wnsgml972.strada.user.domain.UserRepository
import com.wnsgml972.strada.user.domain.UserDetails
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username)
            .map {
                u -> UserDetails(u, Collections.singleton(SimpleGrantedAuthority(u.role)))
            }
            .orElseThrow {
                UserNotFoundException(username)
            }
    }
}
