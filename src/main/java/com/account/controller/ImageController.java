package com.account.controller;

import com.account.config.exception.ResourceNotFoundException;
import com.account.dto.ImageResponseDto;
import com.account.service.impl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ImageController {

    private final ImageServiceImpl imageServiceImpl;

    @PostMapping(value = "/upload-image", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ImageResponseDto uploadAccountData(@RequestPart(name = "image_file") MultipartFile imageFile, @RequestPart(name = "image_type") String imageType) throws IOException, ResourceNotFoundException {
        return imageServiceImpl.uploadImage(imageFile, imageType);
    }

    @PostMapping("/delete-image/{id}")
    public void deleteImage(@PathVariable("id") long id) throws IOException, ResourceNotFoundException {
        imageServiceImpl.deleteImage(id);
    }

}

