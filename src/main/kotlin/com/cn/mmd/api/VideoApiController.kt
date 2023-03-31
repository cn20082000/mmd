package com.cn.mmd.api

import com.cn.mmd.model.other.ResponseModel
import com.cn.mmd.model.request.CreateVideoRequest
import com.cn.mmd.model.request.QueryVideoRequest
import com.cn.mmd.model.request.UpdateVideoRequest
import com.cn.mmd.worker.service.VideoService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/video")
class VideoApiController(
    private val videoService: VideoService,
) {

    @PostMapping
    fun create(@Valid @RequestBody req: CreateVideoRequest): ResponseModel {
        videoService.create(req)
        return ResponseModel.ok(true)
    }

    @PostMapping("/all")
    fun createAll(@Valid @RequestBody req: List<CreateVideoRequest>): ResponseModel {
        val result = videoService.createAll(req)
        return ResponseModel.ok(result)
    }

    @PostMapping("/query")
    fun query(@Valid @RequestBody req: QueryVideoRequest): ResponseModel {
        return ResponseModel.ok(videoService.query(req))
    }

    @PutMapping
    fun update(@Valid @RequestBody req: UpdateVideoRequest): ResponseModel {
        videoService.update(req)
        return ResponseModel.ok(true)
    }

    @DeleteMapping
    fun delete(@Valid @RequestBody req: UpdateVideoRequest): ResponseModel {
        videoService.delete(req)
        return ResponseModel.ok(true)
    }
}