package com.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("img_src")
    private String imageSrc;

    //............Add more what you need
}
