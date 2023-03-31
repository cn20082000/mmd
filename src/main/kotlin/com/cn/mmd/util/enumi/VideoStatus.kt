package com.cn.mmd.util.enumi

import com.cn.mmd.util.converter.VideoStatusDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = VideoStatusDeserializer::class)
enum class VideoStatus {
    NOT_CHECKED,
    PRIVATE,
    NOT_READY,
    IN_QUEUE,
    ACTIVE,
    STORAGE,
}