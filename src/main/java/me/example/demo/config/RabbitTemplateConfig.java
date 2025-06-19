package me.example.demo.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTemplateConfig {

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        factory.setPrefetchCount(5);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setConcurrentConsumers(5);  // 기본 1
        factory.setMaxConcurrentConsumers(10);  // 스케일링 가능

        return factory;
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // RabbitTemplate 설정
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        connectionFactory.isPublisherConfirms();
;
        template.setMandatory(true);
        template.setReturnsCallback(returned -> {
            System.out.println("메시지 라우팅 실패!");
            System.out.println("Exchange: " + returned.getExchange());
            System.out.println("Routing Key: " + returned.getRoutingKey());
            System.out.println("Reply Code: " + returned.getReplyCode());
            System.out.println("Reply Text: " + returned.getReplyText());
            System.out.println("Message: " + new String(returned.getMessage().getBody()));
        });

        template.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("ConfirmCallback 호출, ack=" + ack + ", cause=" + cause);
        });

        return template;
    }
}
