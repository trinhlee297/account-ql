package com.account.controller;

import com.account.config.exception.ResourceNotFoundException;
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
@RequiredArgsConstructor
@RequestMapping("/static/resource")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload-img-data")
    public ResponseEntity<?> uploadAccountData(@RequestParam("file") MultipartFile file) throws IOException, ResourceNotFoundException {
        this.imageService.parseToBase64(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Accounts data uploaded and saved to database successfully"));
    }

}

