package com.kafka.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class KafkaProducerApplication{

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	/*@Bean
	CommandLineRunner commandLineRunnerKafka(ChangesProducer changesProducer) {
		return args -> {

			changesProducer.sendMessage( new Message("Hello From Kafka "

					//+i
					, LocalDateTime.now()));

		};
	}*/
}
