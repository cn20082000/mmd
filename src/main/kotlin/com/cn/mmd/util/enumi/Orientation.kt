package com.cn.mmd.util.enumi

import com.cn.mmd.util.converter.OrientationDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = OrientationDeserializer::class)
enum class Orientation {
    LANDSCAPE,
    PORTRAIT,
}