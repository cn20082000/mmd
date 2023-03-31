package com.cn.mmd.api

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/welcome")
class WelcomeApiController {

    @GetMapping
    fun welcome(): String {
        return "Welcome all!"
    }
}