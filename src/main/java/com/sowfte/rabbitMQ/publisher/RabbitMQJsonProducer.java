package com.sowfte.rabbitMQ.publisher;

import com.sowfte.rabbitMQ.dto.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    private Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);
    @Value("${rabbitmq.que.exchange}")
    private String exchange1;

    @Value("${rabbitmq.que.routing.json.key}")
    private String bindingKey2;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public  void  sendJsonData(UserRecord user){

        LOGGER.info(String.format("Message sent -> %s", user.toString()));

        rabbitTemplate.convertAndSend(exchange1,bindingKey2,user);

    }
}
