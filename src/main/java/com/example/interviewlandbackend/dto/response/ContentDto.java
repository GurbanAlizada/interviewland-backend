package com.example.interviewlandbackend.dto.response;


// Response classlarinda is vacib olan get methodlaridir . Bos constructor olmasi vacib deyil.
public class ContentDto {


    private int id;

    private String contentName;



    public ContentDto(int id , String contentName) {
        this.id = id;
        this.contentName = contentName;
    }


    public int getId() {
        return id;
    }

    public String getContentName() {
        return contentName;
    }


}
