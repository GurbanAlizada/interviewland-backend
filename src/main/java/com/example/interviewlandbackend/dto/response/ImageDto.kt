package com.example.interviewlandbackend.dto.response

import com.example.interviewlandbackend.model.Image

data class ImageDto @JvmOverloads constructor(
    val id: Int?,
    val imageUrl: String,
    val publishId: String
){
    companion object{

        @JvmStatic fun convert(image: Image):ImageDto{
            return ImageDto(
                image.id ,
                image.imageUrl ,
                image.publishId);
        }

    }

}
