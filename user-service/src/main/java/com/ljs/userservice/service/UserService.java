package com.ljs.userservice.service;

public interface UserService {
    /**
     * 사용자 닉네임을 데이터베이스에 저장한다.
     *
     * @param nickname 사용자 닉네임
     */
    void saveNickname(String nickname);
}
