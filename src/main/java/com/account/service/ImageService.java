package com.account.service;

import com.account.config.exception.ResourceNotFoundException;
import com.account.dto.ImageDto;
import com.account.entity.Image;
import com.account.util.ImageUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ImageService {

    public ImageDto uploadImage(MultipartFile file) throws IOException, ResourceNotFoundException {
        final String imageSrc = ImageUtil.parseToBase64(file);

        return null;
    }
}
