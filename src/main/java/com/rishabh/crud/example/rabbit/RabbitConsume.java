package com.rishabh.crud.example.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsume {
    
    @RabbitListener(queues = {"${rabbitmq.queuename}"})
    public void receive(@Payload String message) {
        System.out.println("message consumed - " + message);
    }
}
