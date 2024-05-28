package com.ljs.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljs.userservice.dto.UserDto;
import com.ljs.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<UserDto> json;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @DisplayName("닉네임 생성 API 호출 성공")
    public void saveNicknameSuccessTest() throws Exception {
        // given
        String nickname = "ljs";
        UserDto userDto = new UserDto();
        userDto.setNickname(nickname);
        given(userService.saveUser(any(UserDto.class))).willReturn(userDto);

        // when
        MockHttpServletResponse response = mvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(userDto).getJson()))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(json.write(userDto).getJson(), response.getContentAsString());
    }

    @Test
    @DisplayName("닉네임 생성 API 호출 실패 - 닉네임 누락")
    public void saveNicknameFailTest() throws Exception {
        // given
        UserDto userDto = new UserDto();
        userDto.setNickname(" ");

        // when
        MockHttpServletResponse response = mvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(userDto).getJson()))
                .andReturn().getResponse();

        // then
        String responseBody = response.getContentAsString(Charset.forName("UTF-8"));
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertTrue(responseBody.contains("닉네임을 입력해 주세요."));
    }
}