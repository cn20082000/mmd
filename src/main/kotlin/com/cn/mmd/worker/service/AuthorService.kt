package com.cn.mmd.worker.service

import com.cn.mmd.model.request.CreateAuthorRequest
import com.cn.mmd.model.request.UpdateAuthorRequest
import com.cn.mmd.model.response.AuthorResponse

interface AuthorService {
    fun create(request: CreateAuthorRequest)
    fun getAll(): List<AuthorResponse>
    fun update(request: UpdateAuthorRequest)
    fun delete(request: UpdateAuthorRequest)
}