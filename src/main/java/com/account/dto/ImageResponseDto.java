package com.account.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageResponseDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("image_source")
    private String imageSrc;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;
}
