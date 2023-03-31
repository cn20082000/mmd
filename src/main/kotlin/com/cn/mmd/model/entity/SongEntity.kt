package com.cn.mmd.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "tbl_song")
open class SongEntity : BaseEntity() {

    @Column(name = "_name", nullable = false)
    open var name: String? = null

    @Column(name = "_from", nullable = false)
    open var from: String? = null

    @Column(name = "preview_url")
    open var previewUrl: String? = null

    @Column(name = "description", nullable = false)
    open var description: String? = ""
}