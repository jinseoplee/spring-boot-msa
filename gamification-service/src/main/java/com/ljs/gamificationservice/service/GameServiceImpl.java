package com.ljs.gamificationservice.service;

import com.ljs.gamificationservice.domain.BadgeCard;
import com.ljs.gamificationservice.domain.GameStatistics;
import com.ljs.gamificationservice.repository.BadgeCardRepository;
import com.ljs.gamificationservice.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameServiceImpl implements GameService {
    private final ScoreCardRepository scoreCardRepository;
    private final BadgeCardRepository badgeCardRepository;

    @Override
    public GameStatistics retrieveStatisticsForUser(String userId) {
        int score = scoreCardRepository.getTotalScoreForUser(userId);
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByCreatedDateDesc(userId);
        return new GameStatistics(userId, score, badgeCards.stream()
                .map(BadgeCard::getBadge)
                .collect(Collectors.toList()));
    }
}
