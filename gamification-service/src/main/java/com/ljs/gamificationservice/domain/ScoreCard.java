package com.ljs.gamificationservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 점수와 답안을 연결하는 클래스
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ScoreCard {
    // 기본 점수
    public static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_card_id")
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Long attemptId;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private int score;

    public ScoreCard(String userId, Long attemptId) {
        this(null, userId, attemptId, LocalDateTime.now(), DEFAULT_SCORE);
    }
}
