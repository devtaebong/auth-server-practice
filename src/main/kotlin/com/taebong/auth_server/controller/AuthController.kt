package com.taebong.auth_server.controller

import com.taebong.auth_server.controller.request.SimpleOtpRequestBody
import com.taebong.auth_server.controller.request.SimpleUserRequestBody
import com.taebong.auth_server.service.OtpService
import com.taebong.auth_server.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val otpService: OtpService,
    private val userService: UserService,
) {
    @PostMapping("/api/v1/users/auth")
    fun auth(@RequestBody requestBody: SimpleUserRequestBody): String {
        return userService.auth(requestBody.userId, requestBody.password)
    }

    @PostMapping("/api/v1/otp/check")
    fun checkOtp(@RequestBody requestBody: SimpleOtpRequestBody): Boolean {
        return otpService.checkOpt(requestBody.userId, requestBody.otp)
    }
}
