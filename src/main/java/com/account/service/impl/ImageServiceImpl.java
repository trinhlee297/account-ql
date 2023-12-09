package com.account.service.impl;

import com.account.config.exception.ResourceNotFoundException;
import com.account.dto.ImageResponseDto;
import com.account.entity.Image;
import com.account.enums.ImageType;
import com.account.reponsitory.ImageRepository;
import com.account.service.ImageService;
import com.account.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    private static final String BASE_64_HEADER = "data:image/png;base64, ";
    public ImageResponseDto uploadImage(MultipartFile imageFile, String imageType) throws IOException, ResourceNotFoundException {
        final String imageSrc = BASE_64_HEADER + ImageUtil.parseToBase64(imageFile);

        Image image = new Image();
        image.setImageSrc(imageSrc);
        image.setImageType(ImageType.valueOf(imageType));
        image = imageRepository.save(image);

        return ImageResponseDto.builder()
                .id(image.getId().toString())
                .imageSrc(image.getImageSrc())
                .createdAt(image.getCreatedAt().toString())
                .updatedAt(image.getUpdatedAt().toString())
                .build();
    }

    @Override
    public void deleteImage(Long imageId) throws ResourceNotFoundException {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> {
            log.info("Image {} does not exists", imageId);
            return new ResourceNotFoundException("Image not found");
        });
        imageRepository.delete(image);
        log.info("Delete image {} successfully ", imageId);
    }
}
