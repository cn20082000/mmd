package com.cn.mmd.worker.repository

import com.cn.mmd.model.entity.*
import com.cn.mmd.util.enumi.Orientation
import com.cn.mmd.util.enumi.VideoStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface VideoRepository : CrudRepository<VideoEntity, String> {

    @Query("SELECT true FROM VideoEntity v WHERE v.originalVideoId = ?1")
    fun existByOriginalVideoId(originalVideoId: String): Boolean?

    @Query("SELECT v FROM VideoEntity v WHERE v.isEnabled = true AND v.id = ?1")
    fun getById(id: String): VideoEntity?

    @Query(
        "SELECT v FROM VideoEntity v WHERE v.isEnabled = true " +
                "AND (?1 is null OR lower(v.name) LIKE %?1%) " +
                "AND (?2 is null OR lower(v.originalVideoId) LIKE %?2%) " +
                "AND (?3 = 0 OR v.orientation IN ?4) " +
                "AND (?5 = 0 OR v.status IN ?6) " +
                "AND (?7 is null OR lower(v.fileName) LIKE %?7%) " +
                "AND (?8 is null OR v.author = ?8) " +
                "AND (?9 = (SELECT COUNT(c.id) FROM VideoEntity v2 INNER JOIN v2.characters c WHERE v2.id = v.id AND c IN ?10)) " +
                "AND (?11 = (SELECT COUNT(a.id) FROM VideoEntity v3 INNER JOIN v3.actions a WHERE v3.id = v.id AND a IN ?12)) " +
                "AND (?13 = (SELECT COUNT(s.id) FROM VideoEntity v4 INNER JOIN v4.songs s WHERE v4.id = v.id AND s IN ?14))"
    )
    fun query(
        name: String?,
        originalVideoId: String?,
        orientationCount: Int,
        orientations: List<Orientation>,
        statusCount: Int,
        status: List<VideoStatus>,
        fileName: String?,
        author: AuthorEntity?,
        characterCount: Int,
        characters: List<CharacterEntity>,
        actionCount: Int,
        actions: List<ActionEntity>,
        songCount: Int,
        songs: List<SongEntity>,
        pageable: Pageable
    ): Page<VideoEntity>
}