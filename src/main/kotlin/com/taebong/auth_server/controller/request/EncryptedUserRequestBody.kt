package com.taebong.auth_server.controller.request

import com.taebong.auth_server.annotaion.PasswordEncryption

data class EncryptedUserRequestBody(
    val userId: String,

    @PasswordEncryption
    val password: String,
)
