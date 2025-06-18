package me.example.demo.controller;

import lombok.RequiredArgsConstructor;
import me.example.demo.dto.request.PersonRegisterRequestDto;
import me.example.demo.service.DemoService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> register(@RequestBody PersonRegisterRequestDto dto){
        demoService.register(dto);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/message/a")
    public ResponseEntity<String>  registerMessageA(@RequestBody PersonRegisterRequestDto dto){
        demoService.registerBrokerA(dto);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/message/b")
    public ResponseEntity<String>  registerMessageB(@RequestBody PersonRegisterRequestDto dto){
        demoService.registerBrokerB(dto);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/topic")
    public ResponseEntity<String>  registerTopic(@RequestBody PersonRegisterRequestDto dto){
        demoService.registerTopic(dto);
        return ResponseEntity.ok("OK");
    }
}
