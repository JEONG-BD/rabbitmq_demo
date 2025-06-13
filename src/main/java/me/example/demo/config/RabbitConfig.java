package me.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

    public static final String REGISTER_EXCHANGE_NAME = "demo_register_exchange";
    public static final String REGISTER_QUEUE_NAME = "demo_register_queue";
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


    @Bean(name = "registerExchange")
    public DirectExchange declareExchange() {
        return new DirectExchange(REGISTER_EXCHANGE_NAME);
    }

    @Bean(name = "registerQueue")
    public Queue declareQueue() {
        return new Queue(REGISTER_QUEUE_NAME, true);
    }

    @Bean
    public Binding exchangeBindingQueue(@Qualifier("registerQueue") Queue queue,
                                        @Qualifier("registerExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(REGISTER_ROUTING_KEY);
    }






}
