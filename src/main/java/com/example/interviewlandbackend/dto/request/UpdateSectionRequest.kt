package com.example.interviewlandbackend.dto.request

data class UpdateSectionRequest @JvmOverloads constructor(

    val id: Int,
    val sectionName: String,
    val contentId: Int


) {
}