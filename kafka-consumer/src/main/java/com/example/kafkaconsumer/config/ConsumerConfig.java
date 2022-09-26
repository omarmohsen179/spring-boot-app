package com.example.kafkaconsumer.config;

import com.example.kafkaconsumer.models.Message;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration

public class ConsumerConfig {


    public Map<String, Object> consumerConfig(){
        Map<String, Object> props = new HashMap<>();
        return props;
    }

    @Bean
    public ConsumerFactory<String, Message> consumerFactory(){
        JsonDeserializer<Message> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("com.example");
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                new JsonDeserializer<>()
        );
    }

    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Message>> factory(
            ConsumerFactory<String, Message> consumerFactory
    ){
        ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

}
