package com.taebong.auth_server.controller.request

import java.beans.ConstructorProperties

class SimpleOtpRequestBody @ConstructorProperties("userId", "otp") constructor(
    val userId: String,
    val otp: String,
)
