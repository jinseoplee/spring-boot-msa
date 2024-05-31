package com.ljs.gamificationservice.service;

import com.ljs.gamificationservice.domain.Badge;
import com.ljs.gamificationservice.domain.BadgeCard;
import com.ljs.gamificationservice.domain.GameStatistics;
import com.ljs.gamificationservice.repository.BadgeCardRepository;
import com.ljs.gamificationservice.repository.ScoreCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class GameServiceImplTest {
    private GameService gameService;

    @Mock
    private ScoreCardRepository scoreCardRepository;

    @Mock
    private BadgeCardRepository badgeCardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gameService = new GameServiceImpl(scoreCardRepository, badgeCardRepository);
    }

    @Test
    @DisplayName("사용자 통계 조회 테스트")
    public void retrieveStatisticsForUserTest() {
        // given
        String userId = "ljs";
        int expectedScore = 150;
        BadgeCard bronzeBadge = new BadgeCard(userId, Badge.BRONZE_MULTIPLICATOR);
        List<BadgeCard> badgeCards = Arrays.asList(bronzeBadge);
        GameStatistics expectedGameStatistics = new GameStatistics(userId, expectedScore, badgeCards.stream()
                .map(BadgeCard::getBadge)
                .collect(Collectors.toList()));

        given(scoreCardRepository.getTotalScoreForUser(userId)).willReturn(expectedScore);
        given(badgeCardRepository.findByUserIdOrderByCreatedDateDesc(userId)).willReturn(badgeCards);

        // when
        GameStatistics actualGameStatistics = gameService.retrieveStatisticsForUser(userId);

        // then
        assertEquals(expectedGameStatistics, actualGameStatistics);
    }
}