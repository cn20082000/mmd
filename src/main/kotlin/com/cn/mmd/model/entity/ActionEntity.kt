package com.cn.mmd.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "tbl_action")
open class ActionEntity : BaseEntity() {

    @Column(name = "_name", nullable = false)
    open var name: String? = null

    @Column(name = "description", nullable = false)
    open var description: String? = ""
}