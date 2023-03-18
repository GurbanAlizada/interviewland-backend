package com.example.interviewlandbackend.dto.response

import com.example.interviewlandbackend.model.Section

data class SectionDto @JvmOverloads constructor(

    val id: Int?,
    val sectionName: String

){
    companion object{

        @JvmStatic fun convert(section: Section) : SectionDto{
            return SectionDto(
                section.id,
                section.sectionName
            )
        }

    }


}