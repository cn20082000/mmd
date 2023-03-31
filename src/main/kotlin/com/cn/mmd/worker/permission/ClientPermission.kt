package com.cn.mmd.worker.permission

class ClientPermission : UserPermission {
    override fun createAuthor() = false
    override fun readAuthor() = true
    override fun updateAuthor() = false
    override fun deleteAuthor() = false
    override fun createSong() = false
    override fun readSong() = true
    override fun updateSong() = false
    override fun deleteSong() = false
    override fun createCharacter() = false
    override fun readCharacter() = true
    override fun updateCharacter() = false
    override fun deleteCharacter() = false
    override fun createAction() = false
    override fun readAction() = true
    override fun updateAction() = false
    override fun deleteAction() = false
    override fun createVideo() = false
    override fun readVideo() = true
    override fun updateVideo() = false
    override fun deleteVideo() = false
}