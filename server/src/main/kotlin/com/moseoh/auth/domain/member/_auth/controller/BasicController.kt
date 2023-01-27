package com.moseoh.auth.domain.member._auth.controller

import com.moseoh.auth.domain.member._auth.service.BasicService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth/basic")
class BasicController(
    private val basicService: BasicService
) {
}