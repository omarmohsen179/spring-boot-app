package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.models.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listeners {
    @KafkaListener(topics ="${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
      void listener(Message data) {
        System.out.println("Listener Message: " + data.message() + " ");
        System.out.println("Listener created: " + data.created() + " ");
    }

} // The End of Class;
