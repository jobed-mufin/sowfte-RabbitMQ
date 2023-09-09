package com.sowfte.rabbitMQ.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {
    @Value("${rabbitmq.que.exchange}")
    private String exchange1;

    @Value("${rabbitmq.que.routing.key}")
    private String bindingKey1;

    private  static  final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public  void  sendMessage(String message){
        LOGGER.info(String.format("message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange1,bindingKey1,message);

    }
}
