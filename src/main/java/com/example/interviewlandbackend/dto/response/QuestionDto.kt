package com.example.interviewlandbackend.dto.response

import com.example.interviewlandbackend.model.Question

data class QuestionDto @JvmOverloads constructor(

    val questionId: Int?,
    val questionTitle: String,
    val description: String,
    val sourceCode: String,
    val sectionId: Int?

){

    companion object{

        @JvmStatic fun convert(question: Question) : QuestionDto{
            return QuestionDto(
                question.id,
                question.questionTitle,
                question.description,
                question.sourceCode,
                question.section.id
            )
        }

    }

}
