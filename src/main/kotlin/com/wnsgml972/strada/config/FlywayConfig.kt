package com.wnsgml972.strada.config

import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

@Configuration
@Profile("default")
class FlywayConfig {

    @Bean(initMethod = "migrate")
    fun flyway(dataSource: DataSource): Flyway {
        val flyway = Flyway()
        flyway.isBaselineOnMigrate = true
        flyway.setLocations("classpath:db/migration")
        flyway.dataSource = dataSource
//        flyway.isValidateOnMigrate = true
//        flyway.isCleanDisabled = true
//        flyway.isIgnoreMissingMigrations = true
        flyway.table = "schema_version_strada"
        return flyway
    }
}