package com.taebong.auth_server.util

import java.security.SecureRandom

object OtpCodeUtil {
    fun generateOtpCode(): String {
        val secureRandom = SecureRandom.getInstanceStrong()
        val randomValue = secureRandom.nextInt(900_000) + 100_000
        return randomValue.toString()
    }
}
