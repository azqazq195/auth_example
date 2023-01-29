package com.moseoh.auth.domain.member._oauth.kakao.client

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@Component
class KakaoClient(
    @Value("\${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private val grantType: String,
    @Value("\${spring.security.oauth2.client.registration.kakao.client-id}")
    private val clientId: String,
    @Value("\${spring.security.oauth2.client.registration.kakao.client-secret}")
    private val clientSecret: String,
    @Value("\${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private val redirectUri: String,
    private val webClient: WebClient,
) {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private val accessTokenUri = "https://kauth.kakao.com/oauth/token"
    private val authorizeUri = "https://kauth.kakao.com/oauth/authorize"
    private val userInfoUri = "https://kapi.kakao.com/v2/user/me"

    /**
     * 카카오 AccessToken 발급
     */
    fun authorize(): Any {
        val redirect = "http://localhost:8000/callback"
//        val redirect = redirectUri

        val response = webClient.get()
            .uri("$authorizeUri?client_id=$clientId&client_secret=$clientSecret&redirect_uri=$redirect&response_type=code")
            .retrieve()
            .onStatus(HttpStatusCode::isError) { clientResponse ->
                clientResponse.bodyToMono(String::class.java)
                    .handle { error, sink -> sink.error(RuntimeException(error)) }
            }
            .bodyToMono(String::class.java)
            .block()
        return response!!
    }

    /**
     * 카카오 AccessToken 발급
     */
    fun getAccessToken(code: String): String {
        val params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("code", code)
        params.add("grant_type", grantType)
        params.add("client_id", clientId)
        params.add("client_secret", clientSecret)
        params.add("redirect_uri", redirectUri)

        val webClient = WebClient.create(accessTokenUri)
        val response = webClient.post()
            .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
            .body(BodyInserters.fromFormData(params))
            .retrieve()
            .bodyToMono(Map::class.java)
            .block()
        println(response)
        return response?.get("access_token").toString()
    }

    /**
     * 사용자 정보 가져오기
     */
    fun findProfile(token: String): String {
        val webClient = WebClient.create(userInfoUri)
        val response = webClient.post()
            .uri(userInfoUri)
            .header("Authorization", "Bearer $token")
            .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
            .retrieve()
            .bodyToMono(Map::class.java)
            .block()
        println(response)
        return response?.get("access_token").toString()
    }

    /**
     * 카카오 로그인 사용자 강제 회원가입
     */
//    @Transactional
//    fun saveUser(access_token: String): User? {
//        val profile: KakaoProfile? = findProfile(access_token) //사용자 정보 받아오기
//        var user: User = userRepository.findByUserid(profile.getId())
//
//        //처음이용자 강제 회원가입
//        if (user == null) {
//            user = User.builder()
//                .userid(profile.getId())
//                .password(null) //필요없으니 일단 아무거도 안넣음. 원하는데로 넣으면 됌
//                .nickname(profile.getKakao_account().getProfile().getNickname())
//                .profileImg(profile.getKakao_account().getProfile().getProfile_image_url())
//                .email(profile.getKakao_account().getEmail())
//                .roles("USER")
//                .createTime(LocalDateTime.now())
//                .provider("Kakao")
//                .build()
//            userRepository.save(user)
//        }
//        return user
//    }


    private final fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
            val sb = StringBuilder("Request: \n")
            sb.append("> ${clientRequest.method()} ${clientRequest.url()}\n")
            sb.append("> ${clientRequest.body()}")
            clientRequest
                .headers()
                .forEach { name, values -> values.forEach { value -> sb.append("$name=$value") } }
            logger.debug("{}", sb.toString())
            Mono.just(clientRequest)
        }
    }

    private final fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse ->
            val sb = StringBuilder("Response: \n")
            clientResponse
                .headers()
                .asHttpHeaders().forEach { name, values -> values.forEach { value -> sb.append("$name=$value") } }
            logger.debug("{}", sb.toString())
            Mono.just(clientResponse)
        }
    }

}