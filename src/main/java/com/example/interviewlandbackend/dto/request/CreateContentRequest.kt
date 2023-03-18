package com.example.interviewlandbackend.dto.request
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateContentRequest @JvmOverloads constructor(

    @field:NotBlank
    val contentName: String,
    @field:NotBlank
    val description : String,
    @field:NotNull
    val image: MultipartFile

)
