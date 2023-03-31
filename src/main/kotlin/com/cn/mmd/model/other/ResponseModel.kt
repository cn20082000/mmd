package com.cn.mmd.model.other

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseModel(
    val data: Any? = null,
    val error: ErrorResponseModel? = null,
) {
    companion object {
        fun ok(data: Any?) = ResponseModel(data, null)

        fun error(er: ErrorResponseModel?) = ResponseModel(null, er)
    }
}