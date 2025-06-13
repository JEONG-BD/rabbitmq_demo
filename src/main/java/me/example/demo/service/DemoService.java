package me.example.demo.service;

import lombok.RequiredArgsConstructor;
import me.example.demo.dto.request.PersonRegisterRequestDto;
import me.example.demo.entity.Person;
import me.example.demo.messaging.producer.DemoProducer;
import me.example.demo.repository.DemoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {

    private final DemoRepository demoRepository;
    private final DemoProducer demoProducer;

    public void register(PersonRegisterRequestDto dto) {
        Person person  = Person.builder()
                .name(dto.name())
                .address(dto.address())
                .email(dto.email())
                .build();
        demoRepository.save(person);
    }

    public void registerBroker(PersonRegisterRequestDto dto) {
        demoProducer.sendRegister(dto);
    }
}
