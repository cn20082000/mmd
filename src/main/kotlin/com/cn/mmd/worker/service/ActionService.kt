package com.cn.mmd.worker.service

import com.cn.mmd.model.request.CreateActionRequest
import com.cn.mmd.model.request.UpdateActionRequest
import com.cn.mmd.model.response.ActionResponse

interface ActionService {
    fun create(request: CreateActionRequest)
    fun getAll(): List<ActionResponse>
    fun update(request: UpdateActionRequest)
    fun delete(request: UpdateActionRequest)
}