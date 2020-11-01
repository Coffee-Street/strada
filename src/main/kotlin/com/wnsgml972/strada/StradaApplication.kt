package com.wnsgml972.strada;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class StradaApplication

fun main(args: Array<String>) {
    runApplication<StradaApplication>(*args)
}

