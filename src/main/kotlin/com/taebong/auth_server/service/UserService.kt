package com.taebong.auth_server.service

import com.taebong.auth_server.domain.User
import com.taebong.auth_server.repository.auth.AuthRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val otpService: OtpService,
    private val authRepository: AuthRepository,
    private val encryptService: EncryptService
) {
    fun createNewUser(userId: String, newUser: String): User {
        return authRepository.createNewUser(User(userId, newUser))
    }

    fun auth(userId: String, password: String): String {
        return authRepository.getUserByUserId(userId)
            .takeIf { encryptService.matches(password, it.password) }
            ?.let { otpService.renewOtp(userId) }
            ?: throw RuntimeException()
    }
}
