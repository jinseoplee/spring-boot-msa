package com.ljs.multiplicationservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String requestURI;
}
