package com.cn.mmd.worker.service

import com.cn.mmd.model.request.CreateSongRequest
import com.cn.mmd.model.request.UpdateSongRequest
import com.cn.mmd.model.response.SongResponse

interface SongService {
    fun create(request: CreateSongRequest)
    fun getAll(): List<SongResponse>
    fun update(request: UpdateSongRequest)
    fun delete(request: UpdateSongRequest)
}