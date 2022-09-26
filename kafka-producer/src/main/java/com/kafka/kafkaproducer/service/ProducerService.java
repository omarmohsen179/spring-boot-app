package com.kafka.kafkaproducer.service;

import com.kafka.kafkaproducer.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerService {
    private static final Logger LOGGER= LoggerFactory.getLogger(ProducerService.class);
    private final KafkaTemplate<String, Message> kafkaTemplate;
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    @Autowired
    public ProducerService(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Message message) throws InterruptedException {
        ListenableFuture<SendResult<String, Message>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<>() {


            @Override
            public void onSuccess(SendResult<String, Message> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message+ "] due to : " + ex.getMessage());
            }
        });
    }
}
