package com.taebong.auth_server.entity.otp

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "otp")
class OtpEntity(
    @Column
    val userId: String,

    @Column
    var otpCode: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
) {
    fun renewOtp(newOtpCode: String) {
        this.otpCode = newOtpCode
    }
}
