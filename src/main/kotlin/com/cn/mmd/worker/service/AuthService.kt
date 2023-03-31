package com.cn.mmd.worker.service

import com.cn.mmd.model.entity.UserEntity
import com.cn.mmd.model.request.LoginRequest
import com.cn.mmd.model.request.RegisterRequest
import jakarta.servlet.http.HttpServletRequest

interface AuthService {
    fun login(request: LoginRequest, ip: String): String
    fun register(request: RegisterRequest)
    fun auth(request: HttpServletRequest): UserEntity
}