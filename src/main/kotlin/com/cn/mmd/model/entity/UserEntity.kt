package com.cn.mmd.model.entity

import com.cn.mmd.util.enumi.UserRole
import com.cn.mmd.worker.permission.UserPermission
import jakarta.persistence.*

@Entity
@Table(name = "tbl_user")
open class UserEntity : BaseEntity() {

    @Column(name = "username", nullable = false, unique = true)
    open var username: String? = null

    @Column(name = "hash_password", nullable = false)
    open var hashPassword: String? = null

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    open var role: UserRole? = null

    fun permission() = UserPermission.getPermission(role)
}
