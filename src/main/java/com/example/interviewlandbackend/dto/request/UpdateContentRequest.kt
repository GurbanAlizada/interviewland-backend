package com.example.interviewlandbackend.dto.request

import org.springframework.web.multipart.MultipartFile

data class UpdateContentRequest @JvmOverloads constructor(

    val id: Int,
    val contentName: String,
    val description: String,
    val photo: MultipartFile

)
