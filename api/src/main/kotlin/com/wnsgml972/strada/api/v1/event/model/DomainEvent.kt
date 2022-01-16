package com.wnsgml972.strada.api.v1.event.model

interface DomainEvent {
    val status: String
    val createdMilliseconds: Long
}
