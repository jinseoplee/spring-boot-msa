package com.ljs.gamificationservice.service;

import com.ljs.gamificationservice.domain.LeaderboardRow;

import java.util.List;

public interface LeaderboardService {
    /**
     * 사용자 ID와 총 점수가 포함된 리더보드를 조회한다.
     *
     * @return 사용자 ID와 총 점수가 포함된 리더보드
     */
    List<LeaderboardRow> getCurrentLeaderboard();
}
