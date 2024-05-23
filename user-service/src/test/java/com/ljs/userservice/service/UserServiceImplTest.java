package com.ljs.userservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    private UserService userService;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private SetOperations<String, Object> setOperations;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(redisTemplate);
    }

    @Test
    @DisplayName("닉네임 저장 성공 테스트")
    public void saveNicknameSuccessTest() {
        // given
        String nickname = "ljs";

        // when
        when(redisTemplate.opsForSet()).thenReturn(setOperations);
        userService.saveNickname(nickname);

        // then
        Mockito.verify(setOperations).add("nickname", nickname);
    }
}