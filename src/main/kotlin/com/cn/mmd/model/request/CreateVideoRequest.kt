package com.cn.mmd.model.request

import com.cn.mmd.util.enumi.ValidateError
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateVideoRequest(

    @field:NotNull(message = ValidateError.MISSING_VIDEO_NAME.toString())
    val name: String? = null,

    @field:NotNull(message = ValidateError.MISSING_VIDEO_ORIGINAL_ID.toString())
    val originalVideoId: String? = null,

    @field:NotNull(message = ValidateError.MISSING_AUTHOR_ID.toString())
    val authorId: String? = null,
)
