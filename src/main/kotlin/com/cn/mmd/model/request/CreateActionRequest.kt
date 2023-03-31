package com.cn.mmd.model.request

import com.cn.mmd.util.enumi.ValidateError
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateActionRequest(

    @field:NotNull(message = ValidateError.MISSING_ACTION_NAME.toString())
    val name: String? = null,

    val description: String? = "",
)
