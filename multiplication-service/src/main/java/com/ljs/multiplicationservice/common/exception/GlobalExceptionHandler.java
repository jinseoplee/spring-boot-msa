package com.ljs.multiplicationservice.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 요청 바디가 유효성 검사를 통과하지 못할 때 발생하는 예외를 처리한다.
     *
     * @param e       발생한 예외
     * @param request 클라이언트의 HTTP 요청
     * @return HTTP 상태 코드 400과 오류 메시지를 포함한 ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                               HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = bindingResult.getFieldError().getDefaultMessage();
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, request.getRequestURI());
    }

    /**
     * 요청 바디가 유효하지 않은 JSON 형식일 때 발생하는 예외를 처리한다.
     *
     * @param e       발생한 예외
     * @param request 클라이언트의 HTTP 요청
     * @return HTTP 상태 코드 400과 오류 메시지를 포함한 ResponseEntity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
                                                                               HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "유효하지 않은 JSON 포맷입니다.", request.getRequestURI());
    }

    /**
     * {@link ErrorResponse}를 생성하여 ResponseEntity를 빌드한다.
     *
     * @param status       HTTP 상태 코드
     * @param errorMessage 오류 메시지
     * @param requestURI   요청 URI
     * @return {@link ErrorResponse}를 포함한 ResponseEntity
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String errorMessage, String requestURI) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                errorMessage,
                requestURI
        );

        return new ResponseEntity<>(errorResponse, status);
    }
}
