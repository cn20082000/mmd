package com.cn.mmd.model.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ActionResponse(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
)
