package com.cn.mmd.worker.service

import com.cn.mmd.model.request.CreateCharacterRequest
import com.cn.mmd.model.request.UpdateCharacterRequest
import com.cn.mmd.model.response.CharacterResponse

interface CharacterService {
    fun create(request: CreateCharacterRequest)
    fun getAll(): List<CharacterResponse>
    fun update(request: UpdateCharacterRequest)
    fun delete(request: UpdateCharacterRequest)
}