package com.ljs.authserver.service;

import com.ljs.authserver.dto.SignupRequest;

public interface UserService {
    /**
     * 새로운 사용자를 생성한다.
     *
     * @param signupRequest 회원가입 요청 정보
     */
    void createUser(SignupRequest signupRequest);
}
