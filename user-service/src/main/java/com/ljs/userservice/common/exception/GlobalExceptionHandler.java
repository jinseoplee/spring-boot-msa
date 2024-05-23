package com.ljs.userservice.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                                     HttpServletRequest request) {
        // HTTP 상태 코드 설정
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // 에러 메시지 추출
        BindingResult result = e.getBindingResult();
        String message = result.getFieldErrors().get(0).getDefaultMessage();

        // 응답 본문 구성
        Map<String, String> body = new LinkedHashMap<>();
        body.put("status", "400");
        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        return new ResponseEntity<>(body, status);
    }
}
