package com.ljs.multiplicationservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 곱셈에 대한 정보(A * B)를 나타내는 DTO 클래스
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MultiplicationDto {
    @NotNull(message = "숫자 a를 입력해 주세요.")
    private Integer factorA;

    @NotNull(message = "숫자 b를 입력해 주세요.")
    private Integer factorB;
}