package com.cn.mmd.worker.repository;

import com.cn.mmd.model.entity.UserEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, String> {

    @Query("SELECT u FROM UserEntity u WHERE u.isEnabled = true AND u.username = ?1 AND u.hashPassword = ?2")
    fun getByUsernameAndPassword(username: String, hashPassword: String): UserEntity?

    @Query("SELECT u FROM UserEntity u WHERE u.isEnabled = true AND u.id = ?1")
    fun getById(id: String): UserEntity?

    @Query("SELECT true FROM UserEntity u WHERE u.username = ?1")
    fun existByUsername(username: String): Boolean?
}