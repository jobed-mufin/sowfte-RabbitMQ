package com.sowfte.rabbitMQ.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.que.name}")
    private String queue1;
    @Value("${rabbitmq.que.json.name}")
    private String queuejson;

    @Value("${rabbitmq.que.exchange}")
    private String exchange1;

    @Value("${rabbitmq.que.routing.key}")
    private String bindingKey1;
    @Value("${rabbitmq.que.routing.json.key}")
    private String bindingKey2;



//    bean for rabbitMq QUEUE

    @Bean
    public Queue queue(){
        return  new Queue(queue1);
    }
    @Bean
    public Queue queuejson(){
        return  new Queue(queuejson);


    }
//    bean for rabbitMq exchange

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange1);

    }
//    bean for rabbitMq binding between queue and exchange

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(bindingKey1);
    }
    @Bean
    public Binding bindingjson(){
        return BindingBuilder.bind(queuejson()).to(exchange()).with(bindingKey2);
    }

/**
 * Test are by default set up for us
 * ConnectionFactory
 * RabbitTemplate -> we have to work on that inorder to support our need if not string message will be sent
 * RabbitAdmin
 * */

@Bean
public MessageConverter converter(){
    return new Jackson2JsonMessageConverter();
}

@Bean
public  AmqpTemplate amqpTemplate (ConnectionFactory connectionFactory){

    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(converter());

    return  rabbitTemplate;
}
}
