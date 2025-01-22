package com.taebong.auth_server.exception

class InvalidAuthException(override val message: String? = "사용자 ID 또는 비밀번호가 유효하지 않습니다.") : RuntimeException(message)
