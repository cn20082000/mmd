package com.cn.mmd.model.request

import com.cn.mmd.util.enumi.ValidateError
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateCharacterRequest(

    @field:NotNull(message = ValidateError.MISSING_CHARACTER_NAME.toString())
    val name: String? = null,

    @field:NotNull(message = ValidateError.MISSING_CHARACTER_FROM.toString())
    val from: String? = null,

    val previewUrl: String? = null,
    val description: String? = "",
)
