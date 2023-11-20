package com.account.config.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage"})
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private Instant timestamp;
    private int status;
    private String message;
    private String path;

    public CustomException(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public CustomException(ErrorResponseEnum errorResponseEnum) {
        this.status = errorResponseEnum.status;
        this.message = errorResponseEnum.message;
        this.timestamp = Instant.now();
    }

    public CustomException(String message,int status, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

}
