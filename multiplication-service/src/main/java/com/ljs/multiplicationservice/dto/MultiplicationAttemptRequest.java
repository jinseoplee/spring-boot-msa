package com.ljs.multiplicationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 곱셈 시도에 대한 요청을 나타내는 DTO 클래스
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MultiplicationAttemptRequest {
    @NotBlank(message = "닉네임을 입력해 주세요.")
    private String nickname;

    @JsonProperty("multiplication")
    @NotNull(message = "곱셈 문제를 입력해 주세요.")
    @Valid
    private MultiplicationDto multiplicationDto;

    @NotNull(message = "정답을 입력해 주세요.")
    private Integer answer;
}
