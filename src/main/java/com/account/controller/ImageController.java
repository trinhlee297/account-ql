package com.account.controller;

import com.account.config.exception.ResourceNotFoundException;
import com.account.dto.ImageDto;
import com.account.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/resource")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload-image")
    public ImageDto uploadAccountData(@RequestParam("file") MultipartFile file) throws IOException, ResourceNotFoundException {
        return imageService.uploadImage(file);
    }

}

