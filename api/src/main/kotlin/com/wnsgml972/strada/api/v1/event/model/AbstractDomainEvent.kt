package com.wnsgml972.strada.api.v1.event.model

import java.time.Instant

abstract class AbstractDomainEvent(
    override val createdMilliseconds: Long = Instant.now().toEpochMilli()
) : DomainEvent
