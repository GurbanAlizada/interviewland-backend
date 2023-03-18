package com.example.interviewlandbackend.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateSectionRequest @JvmOverloads constructor(

    @field:NotBlank
    val sectionName: String,

    @field:NotNull
    val contentId: Int


) {
}