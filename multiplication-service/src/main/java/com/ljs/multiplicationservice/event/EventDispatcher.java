package com.ljs.multiplicationservice.event;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 이벤트 버스와의 통신을 처리
 */
@RequiredArgsConstructor
@Component
public class EventDispatcher {
    private final RabbitTemplate rabbitTemplate;

    // Multiplication 정보를 전달하기 위한 익스체인지
    @Value("${multiplication.exchange}")
    private String multiplicationExchange;

    // 이벤트를 전송하기 위한 라우팅 키
    @Value("${multiplication.routing-key}")
    private String multiplicationRoutingKey;


    public void send(MultiplicationSolvedEvent multiplicationSolvedEvent) {
        rabbitTemplate.convertAndSend(
                multiplicationExchange,
                multiplicationRoutingKey,
                multiplicationSolvedEvent
        );
    }
}
