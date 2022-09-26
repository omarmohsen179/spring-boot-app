package com.kafka.kafkaproducer.config;

import com.kafka.kafkaproducer.models.Message;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
//@EnableKafka

public class ProducerConfig {
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;
    @Autowired
    private KafkaAdmin admin;
    public Map<String, Object> producerConfig(){
       admin.initialize();
        admin.setAutoCreate(true);
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }
    // create producer instance
    @Bean
    public ProducerFactory<String, Message> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }
    //set message
    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate(
            ProducerFactory<String, Message> producerFactory
    ){
        return new KafkaTemplate<>(producerFactory);
    }
} // The End of Class;
