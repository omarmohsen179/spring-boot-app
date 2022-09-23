package com.example.kafkaconsumer;

import com.example.kafkaconsumer.Message.Message;
import com.example.kafkaconsumer.Message.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component

public class DatabaseConsumer {

    @KafkaListener(topics = "test", concurrency = "5"  , groupId = "myGroup")
    public void processMessage(String content) {
        System.out.println("Message received: " + content);
    }
}
