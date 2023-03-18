package com.example.interviewlandbackend.dto.request;

import javax.validation.constraints.NotBlank;

public class CreateUserRequst {

    @NotBlank
    private String username;

    @NotBlank
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
