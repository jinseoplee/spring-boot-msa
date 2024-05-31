package com.ljs.gamificationservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 사용자와 전체 점수를 연결하는 리더보드 클래스
 */
@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class LeaderboardRow {
    private final String userId;
    private final Long totalScore;

    public LeaderboardRow() {
        this.userId = "";
        this.totalScore = 0L;
    }
}
