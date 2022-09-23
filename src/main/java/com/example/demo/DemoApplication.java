package com.example.demo;


import com.example.demo.configuration.kafka.ChangesProducer;
import com.example.demo.message.Message;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunnerKafka(ChangesProducer changesProducer) {
        return args -> {

                changesProducer.sendMessage( new Message("Hello From Kafka "

                        //+i
                        , LocalDateTime.now()));

        };
    }
}
