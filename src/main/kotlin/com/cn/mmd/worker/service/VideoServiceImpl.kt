package com.cn.mmd.worker.service

import com.cn.mmd.model.entity.AuthorEntity
import com.cn.mmd.model.entity.VideoEntity
import com.cn.mmd.model.other.PageModel
import com.cn.mmd.model.request.CreateVideoRequest
import com.cn.mmd.model.request.QueryVideoRequest
import com.cn.mmd.model.request.UpdateVideoRequest
import com.cn.mmd.model.response.*
import com.cn.mmd.util.ApiException
import com.cn.mmd.util.enumi.ErrorType
import com.cn.mmd.util.enumi.Orientation
import com.cn.mmd.util.enumi.VideoStatus
import com.cn.mmd.util.isNotNull
import com.cn.mmd.util.isNull
import com.cn.mmd.worker.repository.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class VideoServiceImpl(
    private val actionRepo: ActionRepository,
    private val authorRepo: AuthorRepository,
    private val characterRepo: CharacterRepository,
    private val songRepo: SongRepository,
    private val videoRepo: VideoRepository,
) : VideoService {

    @Transactional
    override fun create(request: CreateVideoRequest) {
        val isDuplicate = videoRepo.existByOriginalVideoId(request.originalVideoId!!)
        if (isDuplicate.isNotNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.DUPLICATE_VIDEO_ORIGINAL_ID)
        }

        val author = authorRepo.getById(request.authorId!!)
        if (author.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.AUTHOR_DOES_NOT_EXIST)
        }

        val video = VideoEntity()
        video.name = request.name
        video.originalVideoId = request.originalVideoId
        video.status = VideoStatus.NOT_CHECKED
        video.author = author

        videoRepo.save(video)
    }

    @Transactional
    override fun createAll(request: List<CreateVideoRequest>): List<Boolean> {
        val authors = mutableListOf<AuthorEntity>()
        val videos = mutableListOf<VideoEntity>()
        val result = mutableListOf<Boolean>()
        for (req in request) {
            val isDuplicate = videoRepo.existByOriginalVideoId(req.originalVideoId!!)
            if (isDuplicate.isNotNull()) {
                result.add(false)
                continue
            }

            var author = authors.find { it.id == req.authorId }
            if (author.isNull()) {
                author = authorRepo.getById(req.authorId!!)
                if (author.isNull()) {
                    result.add(false)
                    continue
                }
            }

            val video = VideoEntity()
            video.name = req.name
            video.originalVideoId = req.originalVideoId
            video.status = VideoStatus.NOT_CHECKED
            video.author = author

            result.add(true)
            videos.add(video)
        }
        videoRepo.saveAll(videos)
        return result.toList()
    }

    override fun query(request: QueryVideoRequest): PageModel {
        val author = request.authorId?.let {
            val res = authorRepo.getById(it)
            if (res.isNull()) {
                throw ApiException()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .error(ErrorType.AUTHOR_DOES_NOT_EXIST)
            }
            res
        }

        val characters = request.characterIds.mapNotNull {
            characterRepo.getById(it)
        }
        if (request.characterIds.isNotEmpty() && characters.isEmpty()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.CHARACTER_DOES_NOT_EXIST)
        }

        val actions = request.actionIds.mapNotNull {
            actionRepo.getById(it)
        }
        if (request.actionIds.isNotEmpty() && actions.isEmpty()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.ACTION_DOES_NOT_EXIST)
        }

        val songs = request.songIds.mapNotNull {
            songRepo.getById(it)
        }
        if (request.songIds.isNotEmpty() && songs.isEmpty()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.SONG_DOES_NOT_EXIST)
        }

        val result = videoRepo.query(
            request.name,
            request.originalVideoId,
            request.orientations.size,
            request.orientations,
            request.status.size,
            request.status,
            request.fileName,
            author,
            characters.size,
            characters,
            actions.size,
            actions,
            songs.size,
            songs,
            request.page.pageable()
        )

        return PageModel(
            result.toList().map {
                VideoResponse(
                    it.id,
                    it.name,
                    it.originalVideoId,
                    it.orientation,
                    it.checkedAt,
                    it.status,
                    it.fileName,
                    it.author?.let { a ->
                        AuthorResponse(
                            a.id,
                            a.name,
                            a.pageUrl,
                            a.videoUrl,
                            a.avatarUrl,
                            a.description,
                            a.alive,
                            a.lastUpdated,
                        )
                    },
                    it.characters.map { c ->
                        CharacterResponse(
                            c.id,
                            c.name,
                            c.from,
                            c.previewUrl,
                            c.description
                        )
                    },
                    it.actions.map { a ->
                        ActionResponse(
                            a.id,
                            a.name,
                            a.description
                        )
                    },
                    it.songs.map { s ->
                        SongResponse(
                            s.id,
                            s.name,
                            s.from,
                            s.previewUrl,
                            s.description
                        )
                    }
                )
            },
            result.pageable.pageNumber,
            result.pageable.pageSize,
            result.numberOfElements,
            result.totalPages,
            result.totalElements
        )
    }

    @Transactional
    override fun update(request: UpdateVideoRequest) {
        val video = videoRepo.getById(request.id!!)
        if (video.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.VIDEO_DOES_NOT_EXIST)
        }

        request.status?.let {
            video.status = it
        }
        if (video.status == VideoStatus.NOT_CHECKED) {
            video.orientation = null
            video.checkedAt = null
            video.fileName = null
            video.actions.clear()
            video.characters.clear()
            video.songs.clear()
        } else {
            request.orientation?.let {
                video.orientation = it
            }
            video.orientation = video.orientation ?: Orientation.LANDSCAPE
            video.checkedAt = LocalDateTime.now()
            request.fileName?.let {
                video.fileName = it
            }
            request.actionIds?.let {
                video.actions.clear()
                for (id in it) {
                    val action = actionRepo.getById(id)
                    action?.let { strongAction ->
                        video.actions.add(strongAction)
                    }
                }
            }
            request.characterIds?.let {
                video.characters.clear()
                for (id in it) {
                    val character = characterRepo.getById(id)
                    character?.let { strongCharacter ->
                        video.characters.add(strongCharacter)
                    }
                }
            }
            request.songIds?.let {
                video.songs.clear()
                for (id in it) {
                    val song = songRepo.getById(id)
                    song?.let { strongSong ->
                        video.songs.add(strongSong)
                    }
                }
            }
        }

        videoRepo.save(video)
    }

    override fun delete(request: UpdateVideoRequest) {
        val video = videoRepo.getById(request.id!!)
        if (video.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.VIDEO_DOES_NOT_EXIST)
        }

        video.isEnabled = false
        videoRepo.save(video)
    }
}