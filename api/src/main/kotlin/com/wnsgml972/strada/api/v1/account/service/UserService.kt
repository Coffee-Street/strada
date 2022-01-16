package com.wnsgml972.strada.api.v1.account.service

import com.wnsgml972.strada.api.v1.account.domain.LoginEvent
import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.api.v1.event.service.DomainEventService
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val domainEventService: DomainEventService,
    private val jwtService: JwtService,
) {
    @Transactional(readOnly = true)
    fun findAll() = userRepository.findAll().map { it.toDto() }

    @Transactional
    fun signUp(id: String, isEnabled: Boolean = true): LoginCompleteResponse {
        val entity = User.of(id, isEnabled)
        return userRepository.save(entity)
            .toDto()
            .let {
                val accessToken = jwtService.createToken(id)

                val event = LoginEvent(LoginEvent.Status.COMPLETE.value, id)
                domainEventService.publishEvent(event)

                LoginCompleteResponse(it, accessToken)
            }
    }

    @Transactional(readOnly = true)
    fun findById(id: String): UserDto {
        return userRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id Not Found") }
            .toDto()
    }
}
