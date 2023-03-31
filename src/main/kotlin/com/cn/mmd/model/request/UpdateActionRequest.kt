package com.cn.mmd.model.request

import com.cn.mmd.util.enumi.ValidateError
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UpdateActionRequest(

    @field:NotNull(message = ValidateError.MISSING_ACTION_ID.toString())
    val id: String? = null,

    val name: String? = null,
    val description: String? = null,
)