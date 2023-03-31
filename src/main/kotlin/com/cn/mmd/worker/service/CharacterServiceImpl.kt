package com.cn.mmd.worker.service

import com.cn.mmd.model.entity.CharacterEntity
import com.cn.mmd.model.request.CreateCharacterRequest
import com.cn.mmd.model.request.UpdateCharacterRequest
import com.cn.mmd.model.response.CharacterResponse
import com.cn.mmd.util.ApiException
import com.cn.mmd.util.enumi.ErrorType
import com.cn.mmd.util.isNull
import com.cn.mmd.worker.repository.CharacterRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CharacterServiceImpl(
    private val characterRepo: CharacterRepository,
) : CharacterService {

    @Transactional
    override fun create(request: CreateCharacterRequest) {
        val character = CharacterEntity()
        character.name = request.name
        character.from = request.from
        character.previewUrl = request.previewUrl
        character.description = request.description

        characterRepo.save(character)
    }

    override fun getAll(): List<CharacterResponse> {
        return characterRepo.getAll().map {
            CharacterResponse(
                it.id,
                it.name,
                it.from,
                it.previewUrl,
                it.description
            )
        }
    }

    @Transactional
    override fun update(request: UpdateCharacterRequest) {
        val character = characterRepo.getById(request.id!!)
        if (character.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.CHARACTER_DOES_NOT_EXIST)
        }

        request.name?.let {
            character.name = it
        }
        request.from?.let {
            character.from = it
        }
        request.previewUrl?.let {
            character.previewUrl = it
        }
        request.description?.let {
            character.description = it
        }

        characterRepo.save(character)
    }

    @Transactional
    override fun delete(request: UpdateCharacterRequest) {
        val character = characterRepo.getById(request.id!!)
        if (character.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.CHARACTER_DOES_NOT_EXIST)
        }

        character.isEnabled = false
        characterRepo.save(character)
    }
}