package com.ljs.gamificationservice.service;

import com.ljs.gamificationservice.domain.GameStatistics;

public interface GameService {
    /**
     * 주어진 사용자의 게임 통계를 조회한다.
     *
     * @param userId 사용자 ID
     * @return 사용자의 통계 정보
     */
    GameStatistics retrieveStatisticsForUser(String userId);
}
