package com.ljs.gamificationservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 사용자 점수와 배지 리스트를 나타낼 때 사용되는 클래스
 */
@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class GameStatistics {
    private final String userId;
    private final int score;
    private final List<Badge> badges;

    public GameStatistics() {
        this.userId = "";
        this.score = 0;
        this.badges = new ArrayList<>();
    }

    /**
     * 0점과 배지 없는 상태를 만들기 위한 팩토리 메서드
     *
     * @param userId 사용자 ID
     * @return {@link GameStatistics} 객체(0점과 배지 없는 상태)
     */
    public static GameStatistics emptyStatistics(String userId) {
        return new GameStatistics(userId, 0, Collections.emptyList());
    }

    /**
     * @return 수정 불가능한 배지 리스트
     */
    public List<Badge> getBadges() {
        return Collections.unmodifiableList(badges);
    }
}
