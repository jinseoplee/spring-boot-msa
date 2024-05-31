package com.ljs.gamificationservice.service;

import com.ljs.gamificationservice.domain.LeaderboardRow;
import com.ljs.gamificationservice.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LeaderboardServiceImpl implements LeaderboardService {
    private final ScoreCardRepository scoreCardRepository;

    @Override
    public List<LeaderboardRow> getCurrentLeaderboard() {
        return scoreCardRepository.findFirst10();
    }
}
