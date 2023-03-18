package com.example.interviewlandbackend.controller;

import com.example.interviewlandbackend.dto.request.LoginRequest;
import com.example.interviewlandbackend.dto.response.TokenResponseDto;
import com.example.interviewlandbackend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }



}
