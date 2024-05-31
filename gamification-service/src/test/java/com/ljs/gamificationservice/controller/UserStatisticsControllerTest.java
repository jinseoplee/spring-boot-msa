package com.ljs.gamificationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljs.gamificationservice.domain.Badge;
import com.ljs.gamificationservice.domain.GameStatistics;
import com.ljs.gamificationservice.service.GameService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserStatisticsController.class)
class UserStatisticsControllerTest {
    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mvc;

    JacksonTester<GameStatistics> json;


    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @DisplayName("사용자 통계 조회 엔드포인트 테스트")
    public void getStatisticsForUserTest() throws Exception {
        // given
        String userId = "ljs";
        int score = 150;
        List<Badge> badges = Arrays.asList(Badge.BRONZE_MULTIPLICATOR);
        GameStatistics gameStatistics = new GameStatistics(userId, score, badges);
        given(gameService.retrieveStatisticsForUser(userId)).willReturn(gameStatistics);

        // when
        MockHttpServletResponse response = mvc.perform(get("/api/statistics")
                        .param("userId", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(json.write(gameStatistics).getJson(), response.getContentAsString());
    }
}