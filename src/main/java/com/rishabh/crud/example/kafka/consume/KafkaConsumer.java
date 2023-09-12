package com.rishabh.crud.example.kafka.consume;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"${spring.kafka.template.default-topic}"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        System.out.println("kafka consumer - " + message);
    }

}
