package com.cn.mmd.model.request

import com.cn.mmd.util.enumi.ValidateError
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UpdateAuthorRequest(

    @field:NotNull(message = ValidateError.MISSING_AUTHOR_ID.toString())
    val id: String? = null,

    val name: String? = null,
    val pageUrl: String? = null,
    val videoUrl: String? = null,
    val avatarUrl: String? = null,
    val description: String? = null,
    val alive: Boolean? = null,
    val lastUpdated: LocalDateTime? = null,
)
