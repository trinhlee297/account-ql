package com.account.service;

import com.account.config.exception.ResourceNotFoundException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ImageService {

    private static final List<String> IMAGE_FILE_TYPE = List.of(
            "jpeg",
            "jpg",
            "png"
    );

    public String parseToBase64(MultipartFile file) throws ResourceNotFoundException, IOException {
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

    public File parseBase64ToImage(String base64Code) throws IOException {
        if (base64Code.isBlank()) {
            throw new IllegalArgumentException("Error");
        }
        File outputFile = new File(System.currentTimeMillis() + "");
        byte[] decodedBytes = Base64.getDecoder().decode(base64Code);
        FileUtils.writeByteArrayToFile(outputFile, decodedBytes);
        return outputFile;
    }

}
