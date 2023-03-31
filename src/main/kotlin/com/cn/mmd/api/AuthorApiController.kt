package com.cn.mmd.api

import com.cn.mmd.model.other.ResponseModel
import com.cn.mmd.model.request.CreateAuthorRequest
import com.cn.mmd.model.request.UpdateAuthorRequest
import com.cn.mmd.worker.service.AuthorService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/author")
class AuthorApiController(
    private val authorService: AuthorService,
) {

    @PostMapping
    fun create(@Valid @RequestBody req: CreateAuthorRequest): ResponseModel {
        authorService.create(req)
        return ResponseModel.ok(true)
    }

    @GetMapping("/all")
    fun getAll(): ResponseModel {
        return ResponseModel.ok(authorService.getAll())
    }

    @PutMapping
    fun update(@Valid @RequestBody req: UpdateAuthorRequest): ResponseModel {
        authorService.update(req)
        return ResponseModel.ok(true)
    }

    @DeleteMapping
    fun delete(@Valid @RequestBody req: UpdateAuthorRequest): ResponseModel {
        authorService.delete(req)
        return ResponseModel.ok(true)
    }
}