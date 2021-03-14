package com.wnsgml972.strada

import com.wnsgml972.strada.config.StradaDomainConfig
import mu.KLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File

@Testcontainers
@SpringBootApplication
@Import(StradaDomainConfig::class)
class StradaTestApplication {

    init {
        mysqlServer.apply {
            start()
        }
    }

    class KDockerComposeContainer(file: File) : DockerComposeContainer<KDockerComposeContainer>(file)

    companion object : KLogging() {
        private val workDirectoryPath = System.getProperty("user.dir")
            .substringBeforeLast("/")

        @Container
        var mysqlServer: DockerComposeContainer<*> = KDockerComposeContainer(File("$workDirectoryPath/api/src/test/resources/docker-compose.yml"))
    }
}
