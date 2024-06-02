package com.ljs.multiplicationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljs.multiplicationservice.dto.MultiplicationAttemptRequest;
import com.ljs.multiplicationservice.dto.MultiplicationAttemptResponse;
import com.ljs.multiplicationservice.dto.MultiplicationDto;
import com.ljs.multiplicationservice.entity.Multiplication;
import com.ljs.multiplicationservice.entity.MultiplicationAttempt;
import com.ljs.multiplicationservice.service.MultiplicationService;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(MultiplicationAttemptController.class)
class MultiplicationAttemptControllerTest {
    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationAttemptRequest> jsonRequest;
    private JacksonTester<MultiplicationAttemptResponse> jsonResponse;
    private JacksonTester<List<MultiplicationAttemptResponse>> jsonResponseList;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @DisplayName("무작위 곱셈 정답 테스트")
    public void postAnswerReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }

    @Test
    @DisplayName("무작위 곱셈 실패 테스트")
    public void postAnswerReturnNotCorrect() throws Exception {
        genericParameterizedTest(false);
    }

    private void genericParameterizedTest(boolean correct) throws Exception {
        // given
        MultiplicationAttemptRequest multiplicationAttemptRequest = new MultiplicationAttemptRequest("ljs", new MultiplicationDto(10, 20), 200);
        MultiplicationAttemptResponse multiplicationAttemptResponse = new MultiplicationAttemptResponse(new MultiplicationDto(10, 20), 200, correct);
        given(multiplicationService.checkAnswer(any(MultiplicationAttemptRequest.class))).willReturn(correct);

        // when
        MockHttpServletResponse response = mvc.perform(post("/api/multiplication/attempt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.write(multiplicationAttemptRequest).getJson()))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonResponse.write(multiplicationAttemptResponse).getJson(), response.getContentAsString());
    }

    @Test
    @DisplayName("사용자의 최근 곱셈 시도 목록을 조회하는 API 테스트")
    public void getUserRecentAttempts() throws Exception {
        // given
        Multiplication multiplication = new Multiplication(10, 20);
        MultiplicationAttempt attempt1 = new MultiplicationAttempt("ljs", multiplication, 200, true);
        MultiplicationAttempt attempt2 = new MultiplicationAttempt("ljs", multiplication, 2000, false);

        List<MultiplicationAttempt> recentAttempts = Arrays.asList(attempt1, attempt2);
        List<MultiplicationAttemptResponse> expectedResponse = recentAttempts.stream()
                .map(MultiplicationAttemptResponse::from)
                .collect(Collectors.toList());

        given(multiplicationService.getUserRecentAttempts("ljs"))
                .willReturn(expectedResponse);

        // when
        MockHttpServletResponse response = mvc.perform(get("/api/multiplication/attempt")
                        .param("nickname", "ljs")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonResponseList.write(expectedResponse).getJson(), response.getContentAsString());
    }

    @Test
    @DisplayName("곱셈 시도 ID로 곱셈 시도를 조회하는 API 테스트")
    public void getAttemptByIdTest() throws Exception {
        // given
        Long attemptId = 1L;
        MultiplicationDto multiplicationDto = new MultiplicationDto(10, 20);
        MultiplicationAttemptResponse expectedResponse = new MultiplicationAttemptResponse(multiplicationDto, 200, false);
        given(multiplicationService.getAttemptById(attemptId)).willReturn(expectedResponse);

        // when
        MockHttpServletResponse response = mvc.perform(get("/api/multiplication/attempt/" + attemptId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonResponse.write(expectedResponse).getJson(), response.getContentAsString());
    }
}