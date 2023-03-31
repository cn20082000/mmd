package com.cn.mmd.model.entity

import com.cn.mmd.util.enumi.Orientation
import com.cn.mmd.util.enumi.VideoStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tbl_video")
open class VideoEntity : BaseEntity() {

    @Column(name = "_name", nullable = false)
    open var name: String? = null

    @Column(name = "original_video_id", nullable = false, unique = true)
    open var originalVideoId: String? = null

    @Column(name = "orientation")
    @Enumerated(EnumType.STRING)
    open var orientation: Orientation? = null

    @Column(name = "checked_at")
    open var checkedAt: LocalDateTime? = null

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    open var status: VideoStatus? = null

    @Column(name = "file_name")
    open var fileName: String? = null

    @ManyToOne
    @JoinColumn(name = "author_id")
    open var author: AuthorEntity? = null

    @ManyToMany
    @JoinTable(name = "tbl_video_character",
        joinColumns = [JoinColumn(name = "video_id")],
        inverseJoinColumns = [JoinColumn(name = "character_id")])
    open var characters = mutableListOf<CharacterEntity>()

    @ManyToMany
    @JoinTable(name = "tbl_video_action",
        joinColumns = [JoinColumn(name = "video_id")],
        inverseJoinColumns = [JoinColumn(name = "action_id")])
    open var actions = mutableListOf<ActionEntity>()

    @ManyToMany
    @JoinTable(name = "tbl_video_song",
        joinColumns = [JoinColumn(name = "video_id")],
        inverseJoinColumns = [JoinColumn(name = "song_id")])
    open var songs = mutableListOf<SongEntity>()
}