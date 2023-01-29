package com.moseoh.auth

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class AppController {
    private final val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping()
    fun hello(): String {
        logger.info("requested hello")
        return "Hello, Auth Example Server."
    }
}