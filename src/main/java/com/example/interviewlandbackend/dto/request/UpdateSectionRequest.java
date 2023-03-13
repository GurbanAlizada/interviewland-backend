package com.example.interviewlandbackend.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateSectionRequest {

    @NotNull
    private int id;

    @NotBlank
    private String sectionName;

    @NotNull
    private int contentId;

    public int getId() {
        return id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public int getContentId() {
        return contentId;
    }


}
