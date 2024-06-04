package com.ljs.authserver.common.exception;

/**
 * 중복된 사용자 ID를 처리하기 위한 사용자 정의 예외 클래스
 */
public class DuplicateUserIdException extends RuntimeException {
    public DuplicateUserIdException(String message) {
        super(message);
    }
}
