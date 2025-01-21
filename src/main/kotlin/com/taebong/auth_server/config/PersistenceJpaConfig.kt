package com.taebong.auth_server.config

import com.taebong.auth_server.entity.EntityModule
import com.taebong.auth_server.repository.RepositoryModule
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import javax.sql.DataSource

@Configuration
@EntityScan(basePackageClasses = [EntityModule::class])
@EnableJpaRepositories(basePackageClasses = [RepositoryModule::class])
class PersistenceJpaConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        return JpaTransactionManager()
    }

    @Bean
    fun writeTransactionManager(transactionManager: PlatformTransactionManager): TransactionTemplate {
        val transactionTemplate = TransactionTemplate(transactionManager)
        transactionTemplate.isReadOnly = false
        return transactionTemplate
    }

    @Bean
    fun readTransactionManager(transactionManager: PlatformTransactionManager): TransactionTemplate {
        val transactionTemplate = TransactionTemplate(transactionManager)
        transactionTemplate.isReadOnly = true
        return transactionTemplate
    }
}
