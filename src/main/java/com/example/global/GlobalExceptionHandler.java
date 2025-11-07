package com.example.global;

import com.example.userss.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthorNotFound(AuthorNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                "error",
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                "error",
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
