package com.taebong.auth_server.controller

import com.taebong.auth_server.controller.request.EncryptedUserRequestBody
import com.taebong.auth_server.domain.User
import com.taebong.auth_server.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {
    @PostMapping("/api/v1/users")
    fun createNewUser(@RequestBody requestBody: EncryptedUserRequestBody): User {
        return userService.createNewUser(requestBody.userId, requestBody.password)
    }
}
