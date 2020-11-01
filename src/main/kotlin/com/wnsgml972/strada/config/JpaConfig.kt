package com.wnsgml972.strada.config

import org.hibernate.cfg.AvailableSettings
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.Properties
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class JpaConfig {

    @Bean
    fun hibernateJpaVendorAdapter(): JpaVendorAdapter {
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setDatabase(Database.MYSQL)
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect")
        return vendorAdapter
    }

    private fun getDefaultHibernateProperties(): Properties {
        val properties = Properties()
        properties.setProperty(AvailableSettings.SHOW_SQL, "false")
        properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "validate")
        properties.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, "false")
        properties.setProperty(AvailableSettings.USE_QUERY_CACHE, "false")
        properties.setProperty(AvailableSettings.GENERATE_STATISTICS, "true")
        properties.setProperty(AvailableSettings.LOG_SESSION_METRICS, "false")
        properties.setProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "false")
        return properties
    }

    @Bean("jpaProperties")
    @Profile("default")
    fun defaultJpaProperties(): Properties {
        val properties = getDefaultHibernateProperties()
        properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update")
        properties.setProperty(AvailableSettings.SHOW_SQL, "true")
        properties.setProperty(AvailableSettings.FORMAT_SQL, "true")
        properties.setProperty(AvailableSettings.CONNECTION_PROVIDER_DISABLES_AUTOCOMMIT, "false")
        properties.setProperty(AvailableSettings.STORAGE_ENGINE, "innodb")
        return properties
    }

    @Bean("jpaProperties")
    @Profile("test")
    fun testJpaProperties(): Properties {
        val properties = getDefaultHibernateProperties()
        properties.setProperty(AvailableSettings.SHOW_SQL, "true")
        return properties
    }

    @Bean("jpaProperties")
    @Profile("production")
    fun productionJpaProperties(): Properties {
        return getDefaultHibernateProperties()
    }

    private fun buildEntityManagerFactoryBean(
        dataSource: DataSource, jpaVendorAdapter: JpaVendorAdapter, jpaProperties: Properties
    ): LocalContainerEntityManagerFactoryBean {
        val factoryBean = LocalContainerEntityManagerFactoryBean()
        factoryBean.setPackagesToScan("com.wnsgml972.strada.*")
        factoryBean.dataSource = dataSource
        factoryBean.jpaVendorAdapter = jpaVendorAdapter
        factoryBean.setJpaProperties(jpaProperties)
        return factoryBean
    }

    @Bean("entityManagerFactory")
    @Profile("default", "production")
    @Primary
    fun entityManagerFactory(
        dataSource: DataSource, jpaVendorAdapter: JpaVendorAdapter, jpaProperties: Properties
    ): LocalContainerEntityManagerFactoryBean {
        return buildEntityManagerFactoryBean(dataSource, jpaVendorAdapter, jpaProperties)
    }

    @Bean
    @Primary
    @Order(20)
    fun transactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory
        return transactionManager
    }

    @Bean("jdbcTemplate", "jdbcTemplates")
    fun jdbcTemplates(dataSource: DataSource): JdbcTemplate {
        val jdbcTemplate = JdbcTemplate()
        jdbcTemplate.dataSource = dataSource
        return jdbcTemplate
    }
}
