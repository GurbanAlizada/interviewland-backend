package com.example.interviewlandbackend.dto.response;

public class SectionDto {

    private int id;

    private String sectionName;


    public SectionDto(int id, String sectionName) {
        this.id = id;
        this.sectionName = sectionName;
    }

    public int getId() {
        return id;
    }

    public String getSectionName() {
        return sectionName;
    }


}
