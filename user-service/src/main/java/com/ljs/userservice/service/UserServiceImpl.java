package com.ljs.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 사용자 닉네임을 Redis에 저장한다.
     *
     * @param nickname 사용자 닉네임
     */
    @Override
    public void saveNickname(String nickname) {
        redisTemplate.opsForSet().add("nickname", nickname);
    }
}
