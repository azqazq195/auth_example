package com.moseoh.auth.config

import io.netty.handler.logging.LogLevel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat

@Configuration
class WebClientConfig {
    @Bean
    @Profile("development")
    fun webClientWithLogging(
        webClientBuilder: WebClient.Builder
    ): WebClient {
        val httpClient = HttpClient
            .create()
            .wiretap("this.javaClass.canonicalName", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL)
        val clientConnector = ReactorClientHttpConnector(httpClient)
        return webClientBuilder
            .clientConnector(clientConnector)
            .build()
    }

    @Bean
    @Profile("!development")
    fun webClientWithoutLogging(
        webClientBuilder: WebClient.Builder
    ): WebClient {
        return webClientBuilder
            .build()
    }
}