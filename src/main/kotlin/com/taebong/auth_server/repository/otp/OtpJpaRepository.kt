package com.taebong.auth_server.repository.otp

import com.taebong.auth_server.entity.otp.OtpEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

fun OtpJpaRepository.getOtpEntityByUserId(userId: String): OtpEntity = findOtpEntityByUserId(userId)
    ?: throw NoSuchElementException("사용자에게 발급된 otp가 존재하지 않습니다. userId: $userId")

@Repository
interface OtpJpaRepository : JpaRepository<OtpEntity, Long> {
    fun findOtpEntityByUserId(userId: String): OtpEntity?
}
