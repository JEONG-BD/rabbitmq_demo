package me.example.demo.controller;

import lombok.RequiredArgsConstructor;
import me.example.demo.dto.request.PersonRegisterRequestDto;
import me.example.demo.service.DemoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    @PostMapping()
    public String register(@RequestBody PersonRegisterRequestDto dto){
        demoService.register(dto);
        return "OK";
    }

    @PostMapping("/message")
    public String registerMessage(@RequestBody PersonRegisterRequestDto dto){
        demoService.registerBroker(dto);
        return "OK";
    }

    @PostMapping("/topic")
    public String registerTopic(@RequestBody PersonRegisterRequestDto dto){
        demoService.registerBroker(dto);
        return "OK";
    }
}
