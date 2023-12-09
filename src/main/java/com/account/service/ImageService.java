package com.account.service;

import com.account.config.exception.ResourceNotFoundException;
import com.account.dto.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageResponseDto uploadImage(MultipartFile imageFile, String imageType) throws IOException, ResourceNotFoundException;
    void deleteImage(Long imageId) throws ResourceNotFoundException;
}
