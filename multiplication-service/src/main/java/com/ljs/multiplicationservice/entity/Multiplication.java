package com.ljs.multiplicationservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 곱셈을 나타내는 클래스(a * b)
 */
@NoArgsConstructor
@Getter
@Entity
public class Multiplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "multiplication_id")
    private Long id;

    private int factorA;
    private int factorB;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
    }
}
