package com.example.interviewlandbackend.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateSectionRequest {

    @NotBlank
    private String sectionName;

    @NotNull
    private int contentId;


    public String getSectionName() {
        return sectionName;
    }

    public int getContentId() {
        return contentId;
    }



}
