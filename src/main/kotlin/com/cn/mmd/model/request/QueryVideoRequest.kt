package com.cn.mmd.model.request

import com.cn.mmd.util.enumi.Orientation
import com.cn.mmd.util.enumi.VideoStatus
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class QueryVideoRequest(
    val name: String? = null,
    val originalVideoId: String? = null,
    val orientations: List<Orientation> = listOf(),
    val status: List<VideoStatus> = listOf(),
    val fileName: String? = null,
    val authorId: String? = null,
    val characterIds: List<String> = listOf(),
    val actionIds: List<String> = listOf(),
    val songIds: List<String> = listOf(),
    val page: PageableRequest = PageableRequest(),
)
