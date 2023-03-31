package com.cn.mmd.util.converter

import com.cn.mmd.util.enumOf
import com.cn.mmd.util.enumi.UserRole
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class UserRoleDeserializer : JsonDeserializer<UserRole?>() {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): UserRole? {
        return p?.valueAsString?.enumOf(UserRole::class.java)
    }
}