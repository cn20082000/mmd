package com.cn.mmd.model.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CharacterResponse(
    val id: String? = null,
    val name: String? = null,
    val from: String? = null,
    val previewUrl: String? = null,
    val description: String? = null,
)
