package com.cn.mmd.worker.permission

class NonPermission : UserPermission {
    override fun createAuthor() = false
    override fun readAuthor() = false
    override fun updateAuthor() = false
    override fun deleteAuthor() = false
    override fun createSong() = false
    override fun readSong() = false
    override fun updateSong() = false
    override fun deleteSong() = false
    override fun createCharacter() = false
    override fun readCharacter() = false
    override fun updateCharacter() = false
    override fun deleteCharacter() = false
    override fun createAction() = false
    override fun readAction() = false
    override fun updateAction() = false
    override fun deleteAction() = false
    override fun createVideo() = false
    override fun readVideo() = false
    override fun updateVideo() = false
    override fun deleteVideo() = false
}