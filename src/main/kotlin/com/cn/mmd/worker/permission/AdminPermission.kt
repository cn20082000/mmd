package com.cn.mmd.worker.permission

class AdminPermission : UserPermission {
    override fun createAuthor() = true
    override fun readAuthor() = true
    override fun updateAuthor() = true
    override fun deleteAuthor() = true
    override fun createSong() = true
    override fun readSong() = true
    override fun updateSong() = true
    override fun deleteSong() = true
    override fun createCharacter() = true
    override fun readCharacter() = true
    override fun updateCharacter() = true
    override fun deleteCharacter() = true
    override fun createAction() = true
    override fun readAction() = true
    override fun updateAction() = true
    override fun deleteAction() = true
    override fun createVideo() = true
    override fun readVideo() = true
    override fun updateVideo() = true
    override fun deleteVideo() = true
}