package com.ljs.multiplicationservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * {@link Multiplication}에 대한 사용자의 답안을 나타내는 클래스
 */
@NoArgsConstructor
@Getter
@Entity
public class MultiplicationAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "multiplication_attempt_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "multiplication_id")
    private Multiplication multiplication;

    private int answer;
    private boolean correct;

    public MultiplicationAttempt(String nickname, Multiplication multiplication, int answer, boolean correct) {
        this.nickname = nickname;
        this.multiplication = multiplication;
        this.answer = answer;
        this.correct = correct;
    }
}
