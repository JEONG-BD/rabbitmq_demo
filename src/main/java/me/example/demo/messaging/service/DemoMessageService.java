package me.example.demo.messaging.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.example.demo.dto.request.PersonRegisterRequestDto;
import me.example.demo.entity.Person;
import me.example.demo.repository.DemoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class DemoMessageService {

    private final DemoRepository demoRepository;

    public void processRegister(PersonRegisterRequestDto message) {
        Person entity = Person.builder().name(message.name())
                .address(message.address())
                .email(message.email())
                .build();

        demoRepository.save(entity);
    }
}
