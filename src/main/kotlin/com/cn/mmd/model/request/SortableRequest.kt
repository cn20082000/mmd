package com.cn.mmd.model.request

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class SortableRequest(
    val isDesc: Boolean? = null,
    val property: String? = null,
)
