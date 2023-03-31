package com.cn.mmd.worker.repository;

import com.cn.mmd.model.entity.SongEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SongRepository : CrudRepository<SongEntity, String> {

    @Query("SELECT s FROM SongEntity s WHERE s.isEnabled = true")
    fun getAll(): List<SongEntity>

    @Query("SELECT s FROM SongEntity s WHERE s.isEnabled = true AND s.id = ?1")
    fun getById(id: String): SongEntity?
}