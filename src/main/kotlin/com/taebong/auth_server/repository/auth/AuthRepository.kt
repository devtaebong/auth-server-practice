package com.taebong.auth_server.repository.auth

import com.taebong.auth_server.transaction.executeOrThrow
import com.taebong.auth_server.domain.User
import com.taebong.auth_server.entity.otp.OtpEntity
import com.taebong.auth_server.repository.otp.OtpJpaRepository
import com.taebong.auth_server.repository.otp.getOtpEntityByUserId
import com.taebong.auth_server.repository.user.UserJpaRepository
import com.taebong.auth_server.repository.user.getUserEntityByUserId
import jakarta.persistence.EntityExistsException
import org.springframework.stereotype.Repository
import org.springframework.transaction.support.TransactionOperations

@Repository
class AuthRepository(
    private val userJpaRepository: UserJpaRepository,
    private val otpJpaRepository: OtpJpaRepository,
    private val readTransactionOperation: TransactionOperations,
    private val writeTransactionOperation: TransactionOperations,
) {
    fun createNewUser(user: User): User {
        return writeTransactionOperation.executeOrThrow {
            userJpaRepository.findUserEntityByUserId(user.userId)?.let {
                throw EntityExistsException("User ${user.userId} already exists")
            }
            val saved = userJpaRepository.save(user.toEntity())
            saved.toDomain()
        }
    }

    fun getUserByUserId(userId: String): User {
        return readTransactionOperation.executeOrThrow {
            userJpaRepository.getUserEntityByUserId(userId).toDomain()
        }
    }

    fun getOtp(userId: String): String {
        return readTransactionOperation.executeOrThrow {
            otpJpaRepository.getOtpEntityByUserId(userId).otpCode
        }
    }

    fun upsertOtp(userId: String, newOtp: String) {
        writeTransactionOperation.executeWithoutResult {
            otpJpaRepository.findOtpEntityByUserId(userId)?.renewOtp(newOtp)
                ?: otpJpaRepository.save(OtpEntity(userId, newOtp))
        }
    }
}
