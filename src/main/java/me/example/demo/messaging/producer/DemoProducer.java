package me.example.demo.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.example.demo.config.RabbitConfig;
import me.example.demo.dto.request.PersonRegisterRequestDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DemoProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendRegisterA(PersonRegisterRequestDto dto) {
        log.info(String.format("Send message -> %s", dto));
        rabbitTemplate.convertAndSend(
                RabbitConfig.REGISTER_EXCHANGE_NAME_A,
                RabbitConfig.REGISTER_ROUTING_KEY,
                dto
        );
    }

    public void sendRegisterB(PersonRegisterRequestDto dto) {
        log.info(String.format("Send message -> %s", dto));
        rabbitTemplate.convertAndSend(
                RabbitConfig.REGISTER_EXCHANGE_NAME_A,
                RabbitConfig.REGISTER_ROUTING_KEY,
                dto
        );
    }

    public void sendTopic(PersonRegisterRequestDto dto) {
        log.info(String.format("Send message -> %s", dto));
        rabbitTemplate.convertAndSend(
                RabbitConfig.REGISTER_EXCHANGE_NAME_A,
                RabbitConfig.REGISTER_ROUTING_KEY,
                dto
        );
    }



}
