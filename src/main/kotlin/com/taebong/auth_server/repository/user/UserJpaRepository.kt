package com.taebong.auth_server.repository.user

import com.taebong.auth_server.entity.user.UserEntity
import com.taebong.auth_server.exception.InvalidAuthException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

fun UserJpaRepository.getUserEntityByUserId(userId: String): UserEntity = findUserEntityByUserId(userId)
    ?: throw InvalidAuthException()

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, Long> {
    fun findUserEntityByUserId(userId: String): UserEntity?
}
