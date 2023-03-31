package com.cn.mmd.worker.repository;

import com.cn.mmd.model.entity.AuthorEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface AuthorRepository : CrudRepository<AuthorEntity, String> {

    @Query("SELECT a FROM AuthorEntity a WHERE a.isEnabled = true")
    fun getAll(): List<AuthorEntity>

    @Query("SELECT a FROM AuthorEntity a WHERE a.isEnabled = true AND a.id = ?1")
    fun getById(id: String): AuthorEntity?
}