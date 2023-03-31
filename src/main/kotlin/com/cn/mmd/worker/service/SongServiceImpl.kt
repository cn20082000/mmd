package com.cn.mmd.worker.service

import com.cn.mmd.model.entity.SongEntity
import com.cn.mmd.model.request.CreateSongRequest
import com.cn.mmd.model.request.UpdateSongRequest
import com.cn.mmd.model.response.SongResponse
import com.cn.mmd.util.ApiException
import com.cn.mmd.util.enumi.ErrorType
import com.cn.mmd.util.isNull
import com.cn.mmd.worker.repository.SongRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SongServiceImpl(
    private val songRepo: SongRepository,
) : SongService {

    @Transactional
    override fun create(request: CreateSongRequest) {
        val song = SongEntity()
        song.name = request.name
        song.from = request.from
        song.previewUrl = request.previewUrl
        song.description = request.description

        songRepo.save(song)
    }

    override fun getAll(): List<SongResponse> {
        return songRepo.getAll().map {
            SongResponse(
                it.id,
                it.name,
                it.from,
                it.previewUrl,
                it.description
            )
        }
    }

    @Transactional
    override fun update(request: UpdateSongRequest) {
        val song = songRepo.getById(request.id!!)
        if (song.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.SONG_DOES_NOT_EXIST)
        }

        request.name?.let {
            song.name = it
        }
        request.from?.let {
            song.from = it
        }
        request.previewUrl?.let {
            song.previewUrl = it
        }
        request.description?.let {
            song.description = it
        }

        songRepo.save(song)
    }

    @Transactional
    override fun delete(request: UpdateSongRequest) {
        val song = songRepo.getById(request.id!!)
        if (song.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.SONG_DOES_NOT_EXIST)
        }

        song.isEnabled = false
        songRepo.save(song)
    }
}