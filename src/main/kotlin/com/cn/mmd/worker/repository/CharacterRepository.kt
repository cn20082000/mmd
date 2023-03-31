package com.cn.mmd.worker.repository;

import com.cn.mmd.model.entity.CharacterEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface CharacterRepository : CrudRepository<CharacterEntity, String> {

    @Query("SELECT c FROM CharacterEntity c WHERE c.isEnabled = true")
    fun getAll(): List<CharacterEntity>

    @Query("SELECT c FROM CharacterEntity c WHERE c.isEnabled = true AND c.id = ?1")
    fun getById(id: String): CharacterEntity?
}