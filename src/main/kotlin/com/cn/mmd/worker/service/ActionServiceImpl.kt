package com.cn.mmd.worker.service

import com.cn.mmd.model.entity.ActionEntity
import com.cn.mmd.model.request.CreateActionRequest
import com.cn.mmd.model.request.UpdateActionRequest
import com.cn.mmd.model.response.ActionResponse
import com.cn.mmd.util.ApiException
import com.cn.mmd.util.enumi.ErrorType
import com.cn.mmd.util.isNull
import com.cn.mmd.worker.repository.ActionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ActionServiceImpl(
    private val actionRepo: ActionRepository,
) : ActionService {

    @Transactional
    override fun create(request: CreateActionRequest) {
        val action = ActionEntity()
        action.name = request.name
        action.description = request.description

        actionRepo.save(action)
    }

    override fun getAll(): List<ActionResponse> {
        return actionRepo.getAll().map {
            ActionResponse(
                it.id,
                it.name,
                it.description
            )
        }
    }

    @Transactional
    override fun update(request: UpdateActionRequest) {
        val action = actionRepo.getById(request.id!!)
        if (action.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.ACTION_DOES_NOT_EXIST)
        }

        request.name?.let {
            action.name = it
        }
        request.description?.let {
            action.description = it
        }

        actionRepo.save(action)
    }

    @Transactional
    override fun delete(request: UpdateActionRequest) {
        val action = actionRepo.getById(request.id!!)
        if (action.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.ACTION_DOES_NOT_EXIST)
        }

        action.isEnabled = false
        actionRepo.save(action)
    }
}