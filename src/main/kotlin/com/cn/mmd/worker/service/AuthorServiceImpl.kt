package com.cn.mmd.worker.service

import com.cn.mmd.model.entity.AuthorEntity
import com.cn.mmd.model.request.CreateAuthorRequest
import com.cn.mmd.model.request.UpdateAuthorRequest
import com.cn.mmd.model.response.AuthorResponse
import com.cn.mmd.util.ApiException
import com.cn.mmd.util.enumi.ErrorType
import com.cn.mmd.util.isNull
import com.cn.mmd.worker.repository.AuthorRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthorServiceImpl(
    private val authorRepo: AuthorRepository,
) : AuthorService {

    @Transactional
    override fun create(request: CreateAuthorRequest) {
        val author = AuthorEntity()
        author.name = request.name
        author.pageUrl = request.pageUrl
        author.videoUrl = request.videoUrl
        author.avatarUrl = request.avatarUrl
        author.description = request.description

        authorRepo.save(author)
    }

    override fun getAll(): List<AuthorResponse> {
        return authorRepo.getAll().map {
            AuthorResponse(
                it.id,
                it.name,
                it.pageUrl,
                it.videoUrl,
                it.avatarUrl,
                it.description,
                it.alive,
                it.lastUpdated,
            )
        }
    }

    @Transactional
    override fun update(request: UpdateAuthorRequest) {
        val author = authorRepo.getById(request.id!!)
        if (author.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.AUTHOR_DOES_NOT_EXIST)
        }

        request.name?.let {
            author.name = it
        }
        request.pageUrl?.let {
            author.pageUrl = it
        }
        request.videoUrl?.let {
            author.videoUrl = it
        }
        request.avatarUrl?.let {
            author.avatarUrl = it
        }
        request.description?.let {
            author.description = it
        }
        request.alive?.let {
            author.alive = it
        }
        request.lastUpdated?.let {
            author.lastUpdated = it
        }

        authorRepo.save(author)
    }

    @Transactional
    override fun delete(request: UpdateAuthorRequest) {
        val author = authorRepo.getById(request.id!!)
        if (author.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.AUTHOR_DOES_NOT_EXIST)
        }

        author.isEnabled = false
        authorRepo.save(author)
    }
}