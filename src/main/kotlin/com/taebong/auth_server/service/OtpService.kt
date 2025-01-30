package com.taebong.auth_server.service

import com.taebong.auth_server.repository.auth.AuthRepository
import com.taebong.auth_server.util.OtpCodeUtil
import org.springframework.stereotype.Service

@Service
class OtpService(private val authRepository: AuthRepository) {
    fun checkOpt(userId: String, sourceOtp: String): Boolean {
        val targetOtp = authRepository.getOtp(userId)
        return targetOtp == sourceOtp
    }

    fun renewOtp(userId: String): String {
        val newOtp = OtpCodeUtil.generateOtpCode()
        authRepository.upsertOtp(userId, newOtp)
        return newOtp
    }
}
