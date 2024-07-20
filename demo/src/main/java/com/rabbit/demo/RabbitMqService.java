package com.rabbit.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Queue 로 메세지 발행 : RabbitTemplate 의 ConvertAndSend 사용
 * Queue 에서 메세지 구독 : @RabbitListener 을 사용
 **/
@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMqService {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    /**
     * 1. Queue 로 메세지를 발행
     * 2. Producer 역할 -> Direct Exchange (메시지의 routing key와 정확히 일치하는 binding된 Queue로 routing)
     **/
    public void sendMessage(MessageDto messageDto) {
        log.info("**Message Send**: {}",messageDto.toString());
        log.info("messagge queue: {}", queueName);
        log.info("messagge exchange: {}", exchangeName);
        log.info("messagge routingKey: {}", routingKey);
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, messageDto);
    }

    /**
     * 1. Queue 에서 메세지를 구독
     * 2. Consumer 역할
     **/
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(MessageDto messageDto) {
        log.info("**Message Received** : {}", messageDto.toString());
    }
}