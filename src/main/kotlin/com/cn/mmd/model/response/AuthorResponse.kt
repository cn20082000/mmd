package com.cn.mmd.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AuthorResponse(
    val id: String? = null,
    val name: String? = null,
    val pageUrl: String? = null,
    val videoUrl: String? = null,
    val avatarUrl: String? = null,
    val description: String? = null,
    val alive: Boolean? = null,

    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val lastUpdated: LocalDateTime? = null,
)