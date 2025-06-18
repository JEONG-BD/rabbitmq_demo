package me.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig {

    public static final String TOPIC_EXCHANGE_NAME = "demo";
    public static final String REGISTER_EXCHANGE_NAME_A = "demo_register_exchange_a";
    public static final String REGISTER_EXCHANGE_NAME_B = "demo_register_exchange_b";
    public static final String REGISTER_QUEUE_NAME_A = "demo_register_queue_a";
    public static final String REGISTER_QUEUE_NAME_B = "demo_register_queue_b";
    public static final String REGISTER_ROUTING_KEY = "demo_register_key";

    //@Value("${rabbitmq.exchange.name}")
    //private String exchange;
    //
    //@Value("${rabbitmq.queue.name}")
    //private String queue;
    //
    //@Value("${rabbitmq.routing.key}")
    //private String routingKey;
    //@Bean
    //public Queue declareQueue(){
    //    return new Queue(queue);
    //}
    //
    //@Bean
    //public DirectExchange declareExchange(){
    //    return new DirectExchange(exchange);
    //}
    //
    //@Bean
    //public Binding exchangeBindingQueue() {
    //    return BindingBuilder.bind(declareQueue())
    //            .to(declareExchange())
    //            .with(routingKey);
    //}


    @Bean(name = "registerExchangeA")
    public DirectExchange declareDirectExchangeA() {
        return new DirectExchange(REGISTER_EXCHANGE_NAME_A);
    }

    @Bean(name = "registerExchangeB")
    public DirectExchange declareDirectExchangeB() {
        return new DirectExchange(REGISTER_EXCHANGE_NAME_B);
    }

    @Bean(name = "topicExchange")
    public TopicExchange declareTopicExchange_A() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean(name = "registerQueueA")
    public Queue declareQueueA() {
        return new Queue(REGISTER_QUEUE_NAME_A, true);
    }

    @Bean(name = "registerQueueB")
    public Queue declareQueueB() {
        return new Queue(REGISTER_QUEUE_NAME_B, true);
    }

    @Bean
    public Binding exchangeBindingQueueA(@Qualifier("registerQueueA") Queue queue,
                                        @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(REGISTER_ROUTING_KEY);
    }
    @Bean

    public Binding exchangeBindingQueueB(@Qualifier("registerQueueB") Queue queue,
                                        @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(REGISTER_ROUTING_KEY);
    }
}
