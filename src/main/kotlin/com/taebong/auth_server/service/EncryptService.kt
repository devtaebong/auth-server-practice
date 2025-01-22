package com.taebong.auth_server.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class EncryptService(private val passwordEncoder: PasswordEncoder) {
    fun encrypt(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }

    fun matches(source: String, target: String): Boolean {
        return passwordEncoder.matches(source, target)
    }
}
