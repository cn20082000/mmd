package com.cn.mmd.model.other

import java.time.LocalDateTime

data class TokenModel(
    val userId: String,
    val userIp: String,
    val createdAt: LocalDateTime,
    val expiredAt: LocalDateTime,
)
