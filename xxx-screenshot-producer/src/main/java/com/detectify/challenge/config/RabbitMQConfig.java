package com.detectify.challenge.config;


import com.detectify.challenge.constant.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vinodjagwani on 11/27/18.
 */
@Configuration
public class RabbitMQConfig {


    @Bean
    public Exchange screenshotsExchange() {
        return ExchangeBuilder.topicExchange(Constants.EXCHANGE_SCREENSHOTS).build();
    }

    @Bean
    public Queue screenshotsQueueA() {
        return QueueBuilder.durable(Constants.QUEUE_SCREENSHOTS_1).build();
    }

    @Bean
    public Queue screenshotsQueueB() {
        return QueueBuilder.durable(Constants.QUEUE_SCREENSHOTS_2).build();
    }

    @Bean
    public Binding bindingA(Queue screenshotsQueueA, TopicExchange screenshotsExchange) {
        return BindingBuilder.bind(screenshotsQueueA).to(screenshotsExchange).with(Constants.QUEUE_SCREENSHOTS_1);
    }

    @Bean
    public Binding bindingB(Queue screenshotsQueueB, TopicExchange screenshotsExchange) {
        return BindingBuilder.bind(screenshotsQueueB).to(screenshotsExchange).with(Constants.QUEUE_SCREENSHOTS_2);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
