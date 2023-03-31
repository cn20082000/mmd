package com.cn.mmd.api

import com.cn.mmd.model.other.ResponseModel
import com.cn.mmd.model.request.CreateCharacterRequest
import com.cn.mmd.model.request.UpdateCharacterRequest
import com.cn.mmd.worker.service.CharacterService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/character")
class CharacterApiController(
    private val characterService: CharacterService,
) {

    @PostMapping
    fun create(@Valid @RequestBody req: CreateCharacterRequest): ResponseModel {
        characterService.create(req)
        return ResponseModel.ok(true)
    }

    @GetMapping("/all")
    fun getAll(): ResponseModel {
        return ResponseModel.ok(characterService.getAll())
    }

    @PutMapping
    fun update(@Valid @RequestBody req: UpdateCharacterRequest): ResponseModel {
        characterService.update(req)
        return ResponseModel.ok(true)
    }

    @DeleteMapping
    fun delete(@Valid @RequestBody req: UpdateCharacterRequest): ResponseModel {
        characterService.delete(req)
        return ResponseModel.ok(true)
    }
}