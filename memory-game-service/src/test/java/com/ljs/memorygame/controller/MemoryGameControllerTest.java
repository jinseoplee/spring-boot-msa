package com.ljs.memorygame.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljs.memorygame.dto.RandomNumberResponse;
import com.ljs.memorygame.service.MemoryGameService;
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

@WebMvcTest(MemoryGameController.class)
class MemoryGameControllerTest {
    @MockBean
    private MemoryGameService memoryGameService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<RandomNumberResponse> json;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @DisplayName("5개의 난수 생성 API 테스트")
    public void getFiveRandomNumbersTest() throws Exception {
        // given
        int[] fiveNumbers = new int[]{1, 24, 3, 17, 5};
        RandomNumberResponse randomNumberResponse = new RandomNumberResponse(fiveNumbers);

        given(memoryGameService.generateFiveRandomNumbers()).willReturn(fiveNumbers);

        // when
        MockHttpServletResponse response = mvc.perform(get("/api/memory-game/random")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(json.write(randomNumberResponse).getJson(), response.getContentAsString());
    }
}