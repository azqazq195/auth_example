package com.moseoh.auth.config

import com.zaxxer.hikari.HikariDataSource
import org.h2.tools.Server
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

@Configuration
@Profile("development")
class H2Config {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun h2TcpServer(): DataSource {
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start()
        return HikariDataSource()
    }
}