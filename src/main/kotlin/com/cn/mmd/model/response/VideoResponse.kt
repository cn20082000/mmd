package com.cn.mmd.model.response

import com.cn.mmd.util.enumi.Orientation
import com.cn.mmd.util.enumi.VideoStatus
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class VideoResponse(
    val id: String? = null,
    val name: String? = null,
    val originalVideoId: String? = null,
    val orientation: Orientation? = null,

    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val checkedAt: LocalDateTime? = null,
    val status: VideoStatus? = null,
    val fileName: String? = null,
    val author: AuthorResponse? = null,
    val characters: List<CharacterResponse>? = null,
    val actions: List<ActionResponse>? = null,
    val songs: List<SongResponse>? = null,
)
