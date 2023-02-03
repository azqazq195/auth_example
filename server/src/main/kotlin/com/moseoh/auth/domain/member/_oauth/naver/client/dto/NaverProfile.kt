package com.moseoh.auth.domain.member._oauth.naver.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverProfile(
    @JsonProperty("resultcode")
    val resultcode: String,
    @JsonProperty("message")
    val message: String,
    @JsonProperty("response")
    val response: Response,
) {
    data class Response(
        @JsonProperty("id")
        val id: String,
        @JsonProperty("email")
        val email: String,
        @JsonProperty("name")
        val name: String,
        @JsonProperty("nickname")
        val nickname: String,
        @JsonProperty("profile_image")
        val profileImage: String,
    )
}
