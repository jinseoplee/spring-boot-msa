package com.ljs.gamificationservice.service;

import com.ljs.gamificationservice.domain.LeaderboardRow;
import com.ljs.gamificationservice.repository.ScoreCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class LeaderboardServiceImplTest {
    private LeaderboardService leaderBoardService;

    @Mock
    private ScoreCardRepository scoreCardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        leaderBoardService = new LeaderboardServiceImpl(scoreCardRepository);
    }

    @Test
    @DisplayName("리더보드 조회 테스트")
    public void retrieveLeaderBoardTest() {
        // given
        LeaderboardRow leaderBoardRow1 = new LeaderboardRow("ljs", 100L);
        List<LeaderboardRow> expectedLeaderboardRow = Arrays.asList(leaderBoardRow1);
        given(scoreCardRepository.findFirst10()).willReturn(expectedLeaderboardRow);

        // when
        List<LeaderboardRow> actualLeaderboardRow = leaderBoardService.getCurrentLeaderboard();

        // then
        assertEquals(expectedLeaderboardRow, actualLeaderboardRow);
    }
}