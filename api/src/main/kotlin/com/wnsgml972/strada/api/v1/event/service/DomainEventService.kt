package com.wnsgml972.strada.api.v1.event.service

import com.wnsgml972.strada.api.v1.event.model.DomainEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class DomainEventService @Autowired constructor(
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun publishEvent(event: DomainEvent) {
        applicationEventPublisher.publishEvent(event)
    }
}
