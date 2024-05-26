package com.ljs.multiplicationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljs.multiplicationservice.dto.MultiplicationDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(MultiplicationController.class)
class MultiplicationControllerTest {
    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationDto> json;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @DisplayName("랜덤 곱셈 생성 요청에 대한 응답 테스트")
    public void getRandomMultiplicationTest() throws Exception {
        // given
        MultiplicationDto multiplicationDto = new MultiplicationDto(50, 30);
        given(multiplicationService.createRandomMultiplication()).willReturn(multiplicationDto);

        // when
        MockHttpServletResponse response = mvc.perform(get("/api/multiplication/random")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        // then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(json.write(multiplicationDto).getJson(), response.getContentAsString());
    }
}