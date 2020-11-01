package com.wnsgml972.strada.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class LocalDateTimeSerializer(
    f: DateTimeFormatter
): JsonSerializer<LocalDateTime>() {

    private val serializer = LocalDateTimeSerializer(f)

    override fun serialize(
        value: LocalDateTime,
        g: JsonGenerator,
        provider: SerializerProvider
    ) {
        val dateTime = getLocalDateTime(value)
        serializer.serialize(dateTime, g, provider)
    }

    private fun getLocalDateTime(value: LocalDateTime): LocalDateTime {
        val instant = value.toInstant(ZoneOffset.systemDefault().rules.getOffset(value))
        return LocalDateTime.from(instant.atOffset(ZoneOffset.UTC))
    }
}