package com.example.springbootwithapachekafka.controller;

import com.example.springbootwithapachekafka.consume.MyTopicConsumer;
import com.example.springbootwithapachekafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaController {

    @Autowired
    private MyTopicConsumer myTopicConsumer;

    @Autowired
    private KafkaTemplate<String, User> template;
    private static final String TOPIC = "myTopic";

    @GetMapping(value = "/kafka/produce")
    public String produce(@RequestParam String message) {
        template.send(TOPIC, new User("Sumon", message));
        return message;
    }

    @GetMapping("/kafka/messages")
    public List<String> getMessages() {
        return myTopicConsumer.getMessages();
    }

}
