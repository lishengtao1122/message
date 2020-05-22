package com.lsdk.service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public Binding queue(){
        Queue queue = new Queue(Constans.MSG_QUEUE_NAME,true);
        Queue activityQueue = new Queue(Constans.MSG_ACTIVITI_QUEUE_NAME,true);
        Exchange exchange = new TopicExchange(Constans.MSG_EXCHANGE,true,false);
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(Constans.MSG_ROUTING_KEY).noargs();
        Binding activitiBinding = BindingBuilder.bind(activityQueue).to(exchange).with(Constans.MSG_ACTIVITI_ROUTING_KEY).noargs();
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareQueue(activityQueue);
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareBinding(binding);
        amqpAdmin.declareBinding(activitiBinding);
        return binding;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer){
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(myMessageConverter());
        return factory;
    }

    private MessageConverter myMessageConverter(){
        return new MessageConverter() {
            @Override
            public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
                return null;
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return null;
            }
        };
    }

}
