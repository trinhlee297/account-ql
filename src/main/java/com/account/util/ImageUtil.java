package com.account.util;

import com.account.config.exception.ResourceNotFoundException;
import com.account.entity.Image;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class ImageUtil {
    private ImageUtil() {}

    private static final List<String> IMAGE_FILE_TYPE = List.of(
            "jpeg",
            "jpg",
            "png"
    );


    public static String parseToBase64(MultipartFile file) throws ResourceNotFoundException, IOException {
        if (null == file || null == file.getContentType() || null == file.getOriginalFilename()) {
            throw new ResourceNotFoundException("Not Found Image ");
        }

        if (IMAGE_FILE_TYPE.stream().anyMatch(t -> file.getContentType().contains(t))) {
            File imageFile = new File(file.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(file.getBytes());
            }
            byte[] fileContent = FileUtils.readFileToByteArray(imageFile);
            return Base64.getEncoder().encodeToString(fileContent);
        } else {
            throw new IllegalArgumentException("File type is not correct");
        }
    }
}
