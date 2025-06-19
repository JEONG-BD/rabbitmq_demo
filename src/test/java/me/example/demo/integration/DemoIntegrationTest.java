package me.example.demo.integration;
import me.example.demo.dto.request.PersonRegisterRequestDto;
import me.example.demo.entity.Person;
import me.example.demo.repository.DemoRepository;
import me.example.demo.service.DemoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.RabbitMQContainer;

import java.util.Optional;

@Testcontainers
@SpringBootTest
public class DemoIntegrationTest {

    @Container
    static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.11-management")
            .withExposedPorts(5672, 15672);

    @DynamicPropertySource
    static void configureRabbitMQ(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
        registry.add("spring.rabbitmq.port", () -> rabbitMQContainer.getMappedPort(5672));
        registry.add("spring.rabbitmq.username", rabbitMQContainer::getAdminUsername);
        registry.add("spring.rabbitmq.password", rabbitMQContainer::getAdminPassword);
    }

    @Autowired
    private DemoService demoService;

    @Autowired
    private DemoRepository personRepository;

    @Test
    @DisplayName("메시리 라우팅에 실패한다")
    void testMessagePublishingAndConsuming() throws InterruptedException {
        // Given
        PersonRegisterRequestDto dto = new PersonRegisterRequestDto("홍길동", "서울", "hong@test.com");

        // When
        demoService.registerBrokerA(dto); // 메시지 발행

        // Then
        Optional<Person> result = personRepository.findByEmail("hong@test.com");
        Assertions.assertFalse(result.isPresent());
    }
}
