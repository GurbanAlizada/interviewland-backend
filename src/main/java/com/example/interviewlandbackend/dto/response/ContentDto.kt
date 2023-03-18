package com.example.interviewlandbackend.dto.response

import com.example.interviewlandbackend.model.Content

data class ContentDto @JvmOverloads constructor(

    val id: Int?,
    val contentName: String,
    val description : String,
    val imageDto: ImageDto

){

    companion object{

        @JvmStatic fun convert(content: Content):ContentDto{
            return ContentDto(
                content.id ,
                content.contentName ,
                content.description ,
                ImageDto.convert(content.image)
            )
        }

    }

}
