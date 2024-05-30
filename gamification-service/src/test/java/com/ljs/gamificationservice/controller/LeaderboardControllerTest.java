package com.ljs.gamificationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljs.gamificationservice.domain.LeaderboardRow;
import com.ljs.gamificationservice.service.LeaderboardService;
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

@WebMvcTest(LeaderboardController.class)
class LeaderboardControllerTest {
    @MockBean
    private LeaderboardService leaderBoardService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<List<LeaderboardRow>> json;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @DisplayName("리더보드 조회 엔드포인트 테스트")
    public void getLeaderboardTest() throws Exception {
        // given
        LeaderboardRow row1 = new LeaderboardRow("ljs", 1000L);
        LeaderboardRow row2 = new LeaderboardRow("cyj", 900L);
        List<LeaderboardRow> leaderboardRow = Arrays.asList(row1, row2);
        given(leaderBoardService.getCurrentLeaderboard()).willReturn(leaderboardRow);

        // when
        MockHttpServletResponse response = mvc.perform(get("/api/leaderboard")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(json.write(leaderboardRow).getJson(), response.getContentAsString());
    }
}