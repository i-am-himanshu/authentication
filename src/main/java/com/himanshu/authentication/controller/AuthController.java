package com.himanshu.authentication.controller;

import com.himanshu.authentication.dto.UserRequest;
import com.himanshu.authentication.dto.UserResponse;
import com.himanshu.authentication.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRequest userRequest) {
        return authService.registerUser(userRequest);
    }
}
