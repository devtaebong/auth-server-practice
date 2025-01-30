package com.taebong.auth_server.controller.request

data class SimpleUserRequestBody(
    val userId: String,

    val password: String,
)
