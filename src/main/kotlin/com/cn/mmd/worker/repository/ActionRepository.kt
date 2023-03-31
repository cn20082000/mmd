package com.cn.mmd.worker.repository;

import com.cn.mmd.model.entity.ActionEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ActionRepository : CrudRepository<ActionEntity, String> {

    @Query("SELECT a FROM ActionEntity a WHERE a.isEnabled = true")
    fun getAll(): List<ActionEntity>

    @Query("SELECT a FROM ActionEntity a WHERE a.isEnabled = true AND a.id = ?1")
    fun getById(id: String): ActionEntity?
}