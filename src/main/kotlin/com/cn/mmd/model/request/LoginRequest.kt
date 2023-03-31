package com.cn.mmd.model.request

import com.cn.mmd.util.enumi.ValidateError
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginRequest(

    @field:NotNull(message = ValidateError.MISSING_USERNAME.toString())
    @field:Size(min = 6, max = 32, message = ValidateError.INVALID_USERNAME.toString())
    val username: String? = null,

    @field:NotNull(message = ValidateError.MISSING_PASSWORD.toString())
    @field:Size(min = 6, max = 32, message = ValidateError.INVALID_PASSWORD.toString())
    val password: String? = null,
)
