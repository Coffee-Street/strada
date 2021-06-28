package com.wnsgml972.strada.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.sql.DataSource

const val ENTITY_BASE_PACKAGES = "com.wnsgml972.strada.api.*"
const val JPA_BASE_PACKAGES = "com.wnsgml972.strada.api.*"

const val PRIMARY_PROPERTIES = "spring.datasource.primary.hikari"
const val PRIMARY_DATASOURCE = "primaryDataSource"

const val SECONDARY_PROPERTIES = "spring.datasource.secondary.hikari"
const val SECONDARY_DATASOURCE = "secondaryDataSource"

@Configuration
@EntityScan(basePackages = [ENTITY_BASE_PACKAGES])
@EnableJpaRepositories(basePackages = [JPA_BASE_PACKAGES],
    repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean::class)
@EnableJpaAuditing
@EnableTransactionManagement
class DataSourceConfiguration {

    @Bean(name = [PRIMARY_DATASOURCE])
    @ConfigurationProperties(PRIMARY_PROPERTIES)
    fun primaryDataSource(): HikariDataSource {
        return DataSourceBuilder
            .create()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean(name = [SECONDARY_DATASOURCE])
    @ConfigurationProperties(SECONDARY_PROPERTIES)
    fun secondaryDataSource(): HikariDataSource {
        return DataSourceBuilder
            .create()
            .type(HikariDataSource::class.java)
            .build()
            .apply { isReadOnly = true }
    }

    @Bean
    @DependsOn(PRIMARY_DATASOURCE, SECONDARY_DATASOURCE)
    fun routingDataSource(
        @Qualifier(PRIMARY_DATASOURCE) primaryDataSource: DataSource,
        @Qualifier(SECONDARY_DATASOURCE) secondaryDataSource: DataSource
    ): DataSource {
        val routingDataSource = RoutingDataSource()
        // TODO infra 구성 끝나면! 라우팅 설정 primary, secondary 해놓기
        // routing 은 잘되는 거 확인했고, 설정도 다 확인했음, 인프라적으로 mysql bin log 이용해서 비동기로 replication 되는 구성만 하면 될 듯
        //  mysql replication example
        //  https://github.com/nitinacquia/mysql-replication-docker-compose/blob/master/master/conf.d/master.cnf
        val dataSources = mapOf<Any, Any>(
            "primary-mysql" to primaryDataSource,
            // TODO infra 구성 끝나면 이거 지우고 아래 주석을 푸세욤
            // TODO "secondary-mysql" to secondaryDataSource
            "secondary-mysql" to primaryDataSource,
        )
        routingDataSource.setTargetDataSources(dataSources)
        routingDataSource.setDefaultTargetDataSource(primaryDataSource)
        return routingDataSource
    }

    @Profile("!test")
    @Primary
    @Bean(name = ["dataSource"])
    @DependsOn("routingDataSource")
    fun dataSource(@Qualifier("routingDataSource") routingDataSource: DataSource) =
        LazyConnectionDataSourceProxy(routingDataSource)

    @Profile("test")
    @Primary
    @Bean(name = ["dataSource"])
    fun testDataSource(@Qualifier(PRIMARY_DATASOURCE) primaryDataSource: DataSource) =
        primaryDataSource
}

class RoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any =
        when {
            TransactionSynchronizationManager.isCurrentTransactionReadOnly() -> "secondary-mysql"
            else -> "primary-mysql"
        }
}
