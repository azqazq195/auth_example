package com.moseoh.auth.domain.member._oauth.naver.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverToken(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("refresh_token")
    val refreshToken: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("expires_in")
    val expiresIn: Int,

    @JsonProperty("error")
    val error: String,
    @JsonProperty("error_description")
    val errorDescription: String
)
