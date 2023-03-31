package com.cn.mmd.util.enumi

import com.cn.mmd.util.converter.UserRoleDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = UserRoleDeserializer::class)
enum class UserRole {
    ADMIN,
    CLIENT,
}