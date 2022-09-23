package com.example.demo.configuration.kafka;

import com.example.demo.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    @KafkaListener(topics ="${spring.kafka.topic.name}", groupId = "group_id")
      void listener(Message data) {
        System.out.println("Listener received: " + data + " ");
    }

} // The End of Class;
