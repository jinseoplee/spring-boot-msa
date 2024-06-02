package com.ljs.multiplicationservice.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 곱셈 서비스에서 문제가 해결됐다는 사실을 모델링한 이벤트
 */
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class MultiplicationSolvedEvent implements Serializable {
    private final Long multiplicationAttemptId;
    private final String userId;
    private final boolean correct;
}
