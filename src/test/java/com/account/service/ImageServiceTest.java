package com.account.service;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {


    private final ImageService imageService = new ImageService();

    private String inputFilePath = "test-image.jpg";

    @Test
     void testParseImage() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(Objects.requireNonNull(classLoader
                        .getResource(inputFilePath))
                .getFile());
        FileInputStream input = new FileInputStream(inputFile);
        MultipartFile multipartFile = new MockMultipartFile("file.jpg",
                inputFile.getName(), "jpg", IOUtils.toByteArray(input));


        String a = imageService.parseToBase64(multipartFile);
        System.out.println(a);
    }
}
