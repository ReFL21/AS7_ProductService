package com.example.demo.MessageBrocker;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String PRODUCT_EXCHANGE    = "product.exchange";
    public static final String PRODUCT_QUEUE       = "product.update.queue";
    public static final String PRODUCT_ROUTING_KEY = "product.update";

    @Bean
    TopicExchange productExchange() {
        return new TopicExchange(PRODUCT_EXCHANGE);
    }
    @Bean
    Queue productQueue() {
        return QueueBuilder.durable(PRODUCT_QUEUE).build();
    }
    @Bean
    Binding productBinding(Queue q, TopicExchange ex) {
        return BindingBuilder.bind(q).to(ex).with(PRODUCT_ROUTING_KEY);
    }


    @Bean
    public Jackson2JsonMessageConverter jacksonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Ensure listeners use JSON converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf,
                                         Jackson2JsonMessageConverter converter) {
        RabbitTemplate tpl = new RabbitTemplate(cf);
        tpl.setMessageConverter(converter);
        return tpl;
    }
}
