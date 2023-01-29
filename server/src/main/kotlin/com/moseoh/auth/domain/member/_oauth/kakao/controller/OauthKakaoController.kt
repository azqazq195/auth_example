package com.moseoh.auth.domain.member._oauth.kakao.controller

import com.moseoh.auth.domain.member._oauth.kakao.client.KakaoClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/v1/member/oauth/kakao")
class OauthKakaoController(
    private val kakaoClient: KakaoClient
) {
    @GetMapping("/callback")
    fun callback(@RequestParam code: String): RedirectView {
        kakaoClient.getAccessToken(code)
        val redirectView = RedirectView()
        redirectView.url = "http://localhost:8000/callback"
        return redirectView
    }

    @GetMapping("/authorize")
    fun login(): Any {
        return kakaoClient.authorize()
    }
}