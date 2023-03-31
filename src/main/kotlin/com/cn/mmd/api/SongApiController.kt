package com.cn.mmd.api

import com.cn.mmd.model.other.ResponseModel
import com.cn.mmd.model.request.CreateSongRequest
import com.cn.mmd.model.request.UpdateSongRequest
import com.cn.mmd.worker.service.SongService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/song")
class SongApiController(
    private val songService: SongService,
) {

    @PostMapping
    fun create(@Valid @RequestBody req: CreateSongRequest): ResponseModel {
        songService.create(req)
        return ResponseModel.ok(true)
    }

    @GetMapping("/all")
    fun getAll(): ResponseModel {
        return ResponseModel.ok(songService.getAll())
    }

    @PutMapping
    fun update(@Valid @RequestBody req: UpdateSongRequest): ResponseModel {
        songService.update(req)
        return ResponseModel.ok(true)
    }

    @DeleteMapping
    fun delete(@Valid @RequestBody req: UpdateSongRequest): ResponseModel {
        songService.delete(req)
        return ResponseModel.ok(true)
    }
}