package com.moseoh.auth.domain._auth.controller

import com.moseoh.auth.domain._auth.service.AuthService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val authService: AuthService
) {
}