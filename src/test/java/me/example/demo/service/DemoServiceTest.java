package me.example.demo.service;

import me.example.demo.dto.request.PersonRegisterRequestDto;
import me.example.demo.entity.Person;
import me.example.demo.messaging.producer.DemoProducer;
import me.example.demo.repository.DemoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DemoServiceTest {


    private DemoRepository demoRepository;
    private DemoProducer demoProducer;
    private DemoService demoService;

    @BeforeEach
    void setUp() {
        demoRepository = mock(DemoRepository.class);
        demoProducer = mock(DemoProducer.class);
        demoService = new DemoService(demoRepository, demoProducer);
    }

    @Test
    void testRegister() {
        PersonRegisterRequestDto dto = new PersonRegisterRequestDto("홍길동", "서울", "hong@test.com");
        demoService.register(dto);
        verify(demoRepository).save(any(Person.class));
    }

    @Test
    void testRegisterBrokerA() {
        PersonRegisterRequestDto dto = new PersonRegisterRequestDto("홍길동", "서울", "hong@test.com");
        demoService.registerBrokerA(dto);
        verify(demoProducer).sendRegisterA(dto);
    }

    @Test
    void testRegisterBrokerB() {
        PersonRegisterRequestDto dto = new PersonRegisterRequestDto("홍길동", "서울", "hong@test.com");
        demoService.registerBrokerB(dto);
        verify(demoProducer).sendRegisterB(dto);
    }

    @Test
    void testRegisterTopic() {
        PersonRegisterRequestDto dto = new PersonRegisterRequestDto("홍길동", "서울", "hong@test.com");
        demoService.registerTopic(dto);
        verify(demoProducer).sendTopic(dto);
    }
}