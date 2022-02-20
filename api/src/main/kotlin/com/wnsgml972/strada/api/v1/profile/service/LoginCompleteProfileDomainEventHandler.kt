package com.wnsgml972.strada.api.v1.profile.service

import com.wnsgml972.strada.api.v1.account.domain.LoginEvent
import com.wnsgml972.strada.api.v1.event.service.DomainEventListener
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class LoginCompleteProfileDomainEventHandler @Autowired constructor(
    private val userProfileService: UserProfileService,
) : DomainEventListener {

    @Async("loginEventHandlerExecutor")
    @EventListener(LoginEvent::class)
    @Transactional
    fun handle(event: LoginEvent) {
        if (!event.isComplete) {
            return
        }

        userProfileService.selectByUserIdOrNull(event.userId)
            ?: run {
                logger.debug { "UserProfile 이 없으면 새로운 프로필을 삽입합니다." }

                val request = UserProfileRequest(event.userId, USER_FIRST_POINT)
                userProfileService.insert(request)
            }

    }

    private val LoginEvent.isComplete
        get() = this.status == LoginEvent.Status.COMPLETE.value

    companion object : KLogging() {
        private const val USER_FIRST_POINT = 0L
    }
}
