package com.cn.mmd.api

import com.cn.mmd.model.other.ResponseModel
import com.cn.mmd.model.request.LoginRequest
import com.cn.mmd.model.request.RegisterRequest
import com.cn.mmd.util.ipAddress
import com.cn.mmd.worker.service.AuthService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/auth")
class AuthApiController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginReq: LoginRequest, req: HttpServletRequest): ResponseModel {
        val token = authService.login(loginReq, req.ipAddress())
        return ResponseModel.ok(token)
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody req: RegisterRequest): ResponseModel {
        authService.register(req)
        return ResponseModel.ok(true)
    }
}