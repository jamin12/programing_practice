package com.example.fcmpractice.controller.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerService {
	//
	// @KafkaListener(topics = "testNewTopic1", groupId = "testgroup")
	// public void consumeMyopic1(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
	// 	log.info("[consume message]: {} from partition: {}", message, partition);
	// }
}