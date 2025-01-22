package com.taebong.auth_server.domain

import com.taebong.auth_server.entity.user.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class User(
    val userId: String,
    val password: String,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword() = password

    override fun getUsername() = userId

    fun toEntity() = UserEntity(userId, password)
}
