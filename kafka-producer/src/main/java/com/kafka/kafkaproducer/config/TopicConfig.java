package com.kafka.kafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class TopicConfig {
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name(topicName).partitions(3).replicas(1).build();
    }
}
