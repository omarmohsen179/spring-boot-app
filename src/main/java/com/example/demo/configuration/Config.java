package com.example.demo.configuration;

import com.example.demo.configuration.test.DemoUtils;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {
    @Bean(name = "applicationDao")
    @Scope(value = "singleton")
    public DemoUtils ApplicationDao() {
        return new DemoUtils();
    }
    @Bean(name = "user")
    @Scope(value = "prototype")
    public User getUser() {
        return null;
    }
    @Autowired
    private KafkaProperties kafkaProperties;

}
