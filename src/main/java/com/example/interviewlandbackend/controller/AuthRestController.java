package com.example.interviewlandbackend.controller;

import com.example.interviewlandbackend.dto.request.LoginRequest;
import com.example.interviewlandbackend.dto.response.TokenResponseDto;
import com.example.interviewlandbackend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin
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
