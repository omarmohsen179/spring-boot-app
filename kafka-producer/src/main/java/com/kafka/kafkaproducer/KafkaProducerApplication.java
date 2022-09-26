package com.kafka.kafkaproducer;

import com.kafka.kafkaproducer.models.Message;
import com.kafka.kafkaproducer.service.ProducerService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import java.time.LocalDateTime;

@SpringBootApplication
public class KafkaProducerApplication{

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}




}
