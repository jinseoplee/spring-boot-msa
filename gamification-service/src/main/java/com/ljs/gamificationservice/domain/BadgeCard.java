package com.ljs.gamificationservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 배지와 사용자를 연결하는 클래스
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class BadgeCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_card_id")
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private Badge badge;

    public BadgeCard(String userId, Badge badge) {
        this(null, userId, LocalDateTime.now(), badge);
    }
}
