package com.example.interviewlandbackend.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CreateUserRequest (

    @field:NotBlank
    val username: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    @field:Email
    val email: String

)
