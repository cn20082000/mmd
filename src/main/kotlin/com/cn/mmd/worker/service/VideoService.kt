package com.cn.mmd.worker.service

import com.cn.mmd.model.other.PageModel
import com.cn.mmd.model.request.CreateVideoRequest
import com.cn.mmd.model.request.QueryVideoRequest
import com.cn.mmd.model.request.UpdateVideoRequest

interface VideoService {
    fun create(request: CreateVideoRequest)
    fun createAll(request: List<CreateVideoRequest>): List<Boolean>
    fun query(request: QueryVideoRequest): PageModel
    fun update(request: UpdateVideoRequest)
    fun delete(request: UpdateVideoRequest)
}