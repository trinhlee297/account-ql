package com.account.config.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ControllerExceptionHandler {
    @ExceptionHandler(CustomException.class) // Catch CustomException ở controller
    public ResponseEntity<CustomException> catchExceptionCustom(CustomException exception, HttpServletRequest request){
        exception.setPath(request.getRequestURI());
        return ResponseEntity.status(exception.getStatus())
                .body(exception);
    }

    // Mehtod bắt lỗi validate
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomException> handleBindException(BindException e, HttpServletRequest request) {
        String errorMessage = "";
        if (e.getBindingResult().hasErrors()){
            for(int i=0;i< e.getBindingResult().getAllErrors().size();i++){
                errorMessage += e.getBindingResult().getAllErrors().get(i).getDefaultMessage();
                errorMessage += (i==e.getBindingResult().getAllErrors().size()-1) ? "." : ", ";
            }
        }
        CustomException appException= new CustomException(400, errorMessage);
        appException.setPath(request.getRequestURI());
        return new ResponseEntity<>(appException, HttpStatus.valueOf(appException.getStatus()));
    }

    @ExceptionHandler(Exception.class) // Catch Các trường hợp còn lại
    public ResponseEntity<CustomException> catchExceptionGlobal(Exception exception, HttpServletRequest request){
        CustomException customException = new CustomException(500, exception.getMessage());
        customException.setPath(request.getRequestURI());
        return ResponseEntity.status(customException.getStatus())
                .body(customException);
    }

}
