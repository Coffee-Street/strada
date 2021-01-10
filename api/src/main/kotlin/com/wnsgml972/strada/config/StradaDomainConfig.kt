package com.wnsgml972.strada.config
import com.wnsgml972.strada.security.JwtProperties
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableConfigurationProperties(
    JwtProperties::class,
)
@Configuration
@EntityScan(basePackages = ["com.wnsgml972.strada.api.*"])

@EnableJpaRepositories(basePackages = ["com.wnsgml972.strada.api.*"],
    repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean::class)
@EnableJpaAuditing
@EnableTransactionManagement

@ComponentScan(basePackages = ["com.wnsgml972.strada.*"])
class StradaDomainConfig
