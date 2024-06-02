package com.ljs.multiplicationservice.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 설정 클래스
 */
@Configuration
public class RabbitMQConfig {
    /**
     * TopicExchange 빈을 생성한다.
     *
     * @param exchangeName 애플리케이션 속성에서 주입된 exchange 이름
     * @return TopicExchange 객체
     */
    @Bean
    public TopicExchange multiplicationExchange(@Value("${multiplication.exchange}") String exchangeName) {
        return new TopicExchange(exchangeName);
    }


    /**
     * RabbitTemplate 빈을 생성한다.
     * 메시지를 RabbitMQ로 발송할 때 사용할 템플릿을 설정한다.
     *
     * @param connectionFactory RabbitMQ 연결 팩토리
     * @return RabbitTemplate 객체
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    /**
     * Jackson2JsonMessageConverter 빈을 생성한다.
     * 메시지를 JSON 형식으로 직렬화/역직렬화하는 데 사용된다.
     *
     * @return Jackson2JsonMessageConverter 객체
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
