package com.wnsgml972.strada.api.v1.account.domain

import com.wnsgml972.strada.api.v1.event.model.AbstractDomainEvent

/**
 * Login Domain Event
 *
 * @param status event 의 status
 * @param userId login 한 유저의 ID
 */
data class LoginEvent(
    override val status: String,
    val userId: String,
) : AbstractDomainEvent() {

    enum class Status(
        val value: String
    ) {
        COMPLETE("COMPLETE");

        companion object {
            fun contains(type: String): Boolean {
                return values().any { it.value == type }
            }
        }
    }
}
