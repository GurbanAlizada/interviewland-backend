package com.example.interviewlandbackend.adapters.inter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryServiceInter {


     Map<String,String> uploadImage(MultipartFile multipartFile);

    void deleteImage(String publishId) throws IOException;





}
