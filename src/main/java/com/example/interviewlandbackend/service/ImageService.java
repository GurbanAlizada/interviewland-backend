package com.example.interviewlandbackend.service;


import com.example.interviewlandbackend.exception.ImageNotFoundException;
import com.example.interviewlandbackend.model.Image;
import com.example.interviewlandbackend.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @Transactional
    public void deleteImage(int id){
        Image image = getImage(id);
        imageRepository.delete(image);
    }



    protected Image getImage(int id){
        return imageRepository.findById(id).orElseThrow(()->new ImageNotFoundException("could not found"));
    }

}
