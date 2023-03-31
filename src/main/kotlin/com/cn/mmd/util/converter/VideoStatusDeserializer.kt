package com.cn.mmd.util.converter

import com.cn.mmd.util.enumOf
import com.cn.mmd.util.enumi.VideoStatus
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class VideoStatusDeserializer : JsonDeserializer<VideoStatus?>() {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): VideoStatus? {
        return p?.valueAsString?.enumOf(VideoStatus::class.java)
    }
}