package com.example.demo.exception;

import com.example.demo.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(
                        MethodArgumentNotValidException ex, HttpServletRequest request) {

                Map<String, String> fieldErrors = new HashMap<>();
                ex.getBindingResult().getAllErrors().forEach(error -> {
                        String fieldName = ((FieldError) error).getField();
                        String errorMessage = error.getDefaultMessage();
                        fieldErrors.put(fieldName, errorMessage);
                });

                log.warn("Validation failed for [{}]: {}", request.getRequestURI(), fieldErrors);

                ApiResponse<Map<String, String>> body = ApiResponse.<Map<String, String>>builder()
                                .success(false)
                                .message("Input validation failed")
                                .data(fieldErrors)
                                .error(ApiResponse.ApiError.builder()
                                                .code("VALIDATION_FAILED")
                                                .message("Validation failed for some fields")
                                                .path(request.getRequestURI())
                                                .build())
                                .build();

                return ResponseEntity.badRequest().body(body);
        }

        @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity<ApiResponse<Void>> handleResponseStatus(
                        ResponseStatusException ex, HttpServletRequest request) {

                HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
                String code = status.name();
                String message = ex.getReason();

                if (ex instanceof AppException appEx) {
                        code = appEx.getErrorCode().name();
                        message = appEx.getCustomMessage();
                }

                log.warn("Response status exception: [{} - {}]: {}", status, request.getRequestURI(), message);

                ApiResponse<Void> body = ApiResponse.<Void>builder()
                                .success(false)
                                .message(message)
                                .error(ApiResponse.ApiError.builder()
                                                .code(code)
                                                .message(message)
                                                .path(request.getRequestURI())
                                                .build())
                                .build();

                return ResponseEntity.status(status).body(body);
        }

        @ExceptionHandler(ConflictException.class)
        public ResponseEntity<ApiResponse<Void>> handleConflict(
                        ConflictException ex, HttpServletRequest request) {

                log.warn("Conflict exception at [{}]: {}", request.getRequestURI(), ex.getMessage());

                ApiResponse<Void> body = ApiResponse.<Void>builder()
                                .success(false)
                                .message(ex.getMessage())
                                .error(ApiResponse.ApiError.builder()
                                                .code("CONFLICT")
                                                .message(ex.getMessage())
                                                .path(request.getRequestURI())
                                                .build())
                                .build();

                return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<ApiResponse<Void>> handleNotFound(
                NotFoundException ex,
                HttpServletRequest request) {

                log.warn("Not found at [{}]: {}",
                        request.getRequestURI(),
                        ex.getMessage());

                ApiResponse<Void> body = ApiResponse.<Void>builder()
                        .success(false)
                        .message(ex.getMessage())
                        .error(ApiResponse.ApiError.builder()
                                .code("NOT_FOUND")
                                .message(ex.getMessage())
                                .path(request.getRequestURI())
                                .build())
                        .build();

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(body);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<Void>> handleGeneral(
                        Exception ex, HttpServletRequest request) {

                log.error("Unexpected error at [{}]: {}", request.getRequestURI(), ex.getMessage(), ex);

                ApiResponse<Void> body = ApiResponse.<Void>builder()
                                .success(false)
                                .message("An unexpected error occurred")
                                .error(ApiResponse.ApiError.builder()
                                                .code("INTERNAL_ERROR")
                                                .message(ex.getMessage())
                                                .path(request.getRequestURI())
                                                .build())
                                .build();

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
}
