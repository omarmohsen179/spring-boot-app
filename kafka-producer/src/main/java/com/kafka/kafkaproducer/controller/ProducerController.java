package com.kafka.kafkaproducer.controller;

import com.kafka.kafkaproducer.service.ProducerService;
import com.kafka.kafkaproducer.models.Message;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ProducerController {
    @Autowired
    ProducerService changesProducer;
    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable("message")
                                 final String message) throws InterruptedException {
        changesProducer.sendMessage( new Message(message , LocalDateTime.now()));
        return "Published Successfully";
    }
}
