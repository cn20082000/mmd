package com.cn.mmd.util.converter

import com.cn.mmd.util.enumOf
import com.cn.mmd.util.enumi.Orientation
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class OrientationDeserializer : JsonDeserializer<Orientation?>() {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Orientation? {
        return p?.valueAsString?.enumOf(Orientation::class.java)
    }
}