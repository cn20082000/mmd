package com.cn.mmd.worker.permission

import com.cn.mmd.util.enumi.UserRole

interface UserPermission {
    // Quyền liên quan đến Người tạo ra video (Author)
    fun createAuthor(): Boolean
    fun readAuthor(): Boolean
    fun updateAuthor(): Boolean
    fun deleteAuthor(): Boolean

    // Quyền liên quan đến Bài hát (Song)
    fun createSong(): Boolean
    fun readSong(): Boolean
    fun updateSong(): Boolean
    fun deleteSong(): Boolean

    // Quyền liên quan đến Nhân vật (Character)
    fun createCharacter(): Boolean
    fun readCharacter(): Boolean
    fun updateCharacter(): Boolean
    fun deleteCharacter(): Boolean

    // Quyền liên quan đến Hành động (Action)
    fun createAction(): Boolean
    fun readAction(): Boolean
    fun updateAction(): Boolean
    fun deleteAction(): Boolean

    // Quyền liên quan đến Video
    fun createVideo(): Boolean
    fun readVideo(): Boolean
    fun updateVideo(): Boolean
    fun deleteVideo(): Boolean

    companion object {
        fun getPermission(role: UserRole?) : UserPermission {
            return when (role) {
                UserRole.ADMIN -> AdminPermission()
                UserRole.CLIENT -> ClientPermission()
                else -> NonPermission()
            }
        }
    }
}