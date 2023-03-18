package com.example.interviewlandbackend.dto.request

data class CreateQuestionRequest @JvmOverloads constructor(


    val questionTitle: String,
    val description: String,
    val sourceCode: String,
    val sectionId : Int


){
}