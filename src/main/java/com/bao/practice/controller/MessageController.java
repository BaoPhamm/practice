package com.bao.practice.controller;

import com.bao.practice.config.KafkaConfigProps;
import com.bao.practice.dto.MessageEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfigProps kafkaConfigProps;
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    public MessageController(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate, KafkaConfigProps kafkaConfigProps) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfigProps = kafkaConfigProps;
    }

    @GetMapping("/test")
    public String testController() {
        log.info("Test logging !!!");
        return "Controller Works fine!";
    }

    @PostMapping("")
    public String sendMessage(@RequestBody MessageEvent messageEvent) throws JsonProcessingException {
        log.info("Send message");
        messageEvent.setMessageId(UUID.randomUUID().toString());
        messageEvent.setDateTime(LocalDateTime.now());

        final String payload = objectMapper.writeValueAsString(messageEvent);
        kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
        return payload;
    }


    @KafkaListener(topics = "bao.topic1")
    public String listen(final String in) {
        System.out.println(in);
        return in;
    }

}
