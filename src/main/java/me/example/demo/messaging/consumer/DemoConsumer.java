package me.example.demo.messaging.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.example.demo.config.RabbitConfig;
import me.example.demo.dto.request.PersonRegisterRequestDto;
import me.example.demo.messaging.service.DemoMessageService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class DemoConsumer {
    private final RabbitTemplate rabbitTemplate;
    private final DemoMessageService messageService;

    @RabbitListener(queues = RabbitConfig.REGISTER_QUEUE_NAME_A)
    public void receiveRegisterA(PersonRegisterRequestDto message, Message properties) {

        MessageProperties messageProperties = properties.getMessageProperties();
        String contentEncoding = messageProperties.getContentEncoding();
        MessageDeliveryMode deliveryMode = messageProperties.getDeliveryMode();
        String expiration = messageProperties.getExpiration();
        String correlationId = messageProperties.getCorrelationId();
        String messageId = messageProperties.getMessageId();
        String appId = messageProperties.getAppId();
        Map<String, Object> headers = messageProperties.getHeaders();

        log.info(String.format("Received message -> %s", message));
        messageService.processRegister(message);
    }

    @RabbitListener(queues = RabbitConfig.REGISTER_QUEUE_NAME_B)
    public void receiveRegisterB(PersonRegisterRequestDto message) {
        log.info(String.format("Received message -> %s", message));
        messageService.processRegister(message);
    }
}
