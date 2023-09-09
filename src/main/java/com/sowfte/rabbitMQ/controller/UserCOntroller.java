package com.sowfte.rabbitMQ.controller;

import com.sowfte.rabbitMQ.dto.UserRecord;
import com.sowfte.rabbitMQ.publisher.RabbitMQJsonProducer;
import com.sowfte.rabbitMQ.publisher.RabbitMqProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserCOntroller {
    private Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

    private RabbitMQJsonProducer rabbitMQJsonProducer;

    public UserCOntroller(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping()
    public ResponseEntity<String> sendJsonMessage(@RequestBody UserRecord user){
        LOGGER.info(String.format("User record sent -> %s ", user.toString()));
        rabbitMQJsonProducer.sendJsonData(user);

        return ResponseEntity.ok(user.toString());
    }


}
