package com.example.interviewlandbackend.dto.request

data class UpdateQuestionRequest @JvmOverloads constructor(

    val questionId: Int,
    val questionTitle: String,
    val description: String,
    val sourceCode: String,
    val sectionId: Int

)
