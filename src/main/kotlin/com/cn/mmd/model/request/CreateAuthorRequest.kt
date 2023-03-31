package com.cn.mmd.model.request

import com.cn.mmd.util.enumi.ValidateError
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateAuthorRequest(

    @field:NotNull(message = ValidateError.MISSING_AUTHOR_NAME.toString())
    val name: String? = null,

    @field:NotNull(message = ValidateError.MISSING_AUTHOR_PAGE.toString())
    val pageUrl: String? = null,

    @field:NotNull(message = ValidateError.MISSING_AUTHOR_VIDEO.toString())
    val videoUrl: String? = null,

    @field:NotNull(message = ValidateError.MISSING_AUTHOR_AVATAR.toString())
    val avatarUrl: String? = null,

    val description: String? = "",
)
