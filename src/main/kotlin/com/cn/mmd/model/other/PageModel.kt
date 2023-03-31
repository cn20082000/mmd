package com.cn.mmd.model.other

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PageModel(
    val data: Any,
    val page: Int,
    val size: Int,
    val elements: Int,
    val totalPages: Int,
    val totalElements: Long,
)
