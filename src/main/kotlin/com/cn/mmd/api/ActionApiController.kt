package com.cn.mmd.api

import com.cn.mmd.model.other.ResponseModel
import com.cn.mmd.model.request.CreateActionRequest
import com.cn.mmd.model.request.UpdateActionRequest
import com.cn.mmd.worker.service.ActionService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/action")
class ActionApiController(
    private val actionService: ActionService,
) {

    @PostMapping
    fun create(@Valid @RequestBody req: CreateActionRequest): ResponseModel {
        actionService.create(req)
        return ResponseModel.ok(true)
    }

    @GetMapping("/all")
    fun getAll(): ResponseModel {
        return ResponseModel.ok(actionService.getAll())
    }

    @PutMapping
    fun update(@Valid @RequestBody req: UpdateActionRequest): ResponseModel {
        actionService.update(req)
        return ResponseModel.ok(true)
    }

    @DeleteMapping
    fun delete(@Valid @RequestBody req: UpdateActionRequest): ResponseModel {
        actionService.delete(req)
        return ResponseModel.ok(true)
    }
}