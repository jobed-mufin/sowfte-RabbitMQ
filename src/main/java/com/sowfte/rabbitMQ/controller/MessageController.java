package com.sowfte.rabbitMQ.controller;

import com.sowfte.rabbitMQ.publisher.RabbitMqProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    private RabbitMqProducer rabbitMqProducer;

    public MessageController(RabbitMqProducer rabbitMqProducer) {
        this.rabbitMqProducer = rabbitMqProducer;
    }

    @GetMapping("/publish")

    public ResponseEntity<String>  sendMessage(@RequestParam("message") String message){
        rabbitMqProducer.sendMessage(message);

        return  ResponseEntity.ok("Message sent to RabbitMQ...");
    }
}
