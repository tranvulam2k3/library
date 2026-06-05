package com.example.demo.exception;

import com.example.demo.enums.ErrorCode;
import lombok.Getter;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AppException extends ResponseStatusException {
    private final ErrorCode errorCode;
    private final String customMessage;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.getMessage());
        this.errorCode = errorCode;
        this.customMessage = errorCode.getMessage();
    }

    public AppException(ErrorCode errorCode, String customMessage) {
        super(errorCode.getStatus(), customMessage);
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }
}
