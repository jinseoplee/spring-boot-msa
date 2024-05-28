package com.ljs.userservice.service;

import com.ljs.userservice.dto.UserDto;

public interface UserService {
    /**
     * 사용자 정보를 데이터베이스에 저장한다.
     *
     * @param userDto {@link UserDto} 객체
     * @return {@link UserDto} 객체
     */
    UserDto saveUser(UserDto userDto);
}