package com.example.demo.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "Book not found"),
    PUBLISHER_NOT_FOUND(HttpStatus.NOT_FOUND, "Publisher not found"),
    PUBLISHER_ALREADY_EXISTS(HttpStatus.CONFLICT, "Publisher name already exists");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
