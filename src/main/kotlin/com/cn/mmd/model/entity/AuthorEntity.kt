package com.cn.mmd.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "tbl_author")
open class AuthorEntity : BaseEntity() {

    @Column(name = "_name", nullable = false)
    open var name: String? = null

    @Column(name = "page_url", nullable = false)
    open var pageUrl: String? = null

    @Column(name = "video_url", nullable = false)
    open var videoUrl: String? = null

    @Column(name = "avatar_url", nullable = false)
    open var avatarUrl: String? = null

    @Column(name = "description", nullable = false)
    open var description: String? = ""

    @Column(name = "alive", nullable = false)
    open var alive: Boolean = true

    @Column(name = "last_updated")
    open var lastUpdated: LocalDateTime? = null
}