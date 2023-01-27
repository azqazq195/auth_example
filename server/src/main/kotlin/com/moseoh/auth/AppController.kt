package com.moseoh.auth

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class AppController {
    @GetMapping()
    fun hello(): String {
        return "Hello, Auth Example Server."
    }
}