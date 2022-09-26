package com.kafka.kafkaproducer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



public record Message(String message, LocalDateTime created) {


}
