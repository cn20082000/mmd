package com.cn.mmd.model.request

import com.cn.mmd.util.enumi.Orientation
import com.cn.mmd.util.enumi.ValidateError
import com.cn.mmd.util.enumi.VideoStatus
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UpdateVideoRequest(

    @field:NotNull(message = ValidateError.MISSING_VIDEO_ID.toString())
    val id: String? = null,

    val orientation: Orientation? = null,
    val status: VideoStatus? = null,
    val fileName: String? = null,
    val actionIds: List<String>? = null,
    val characterIds: List<String>? = null,
    val songIds: List<String>? = null,
)
