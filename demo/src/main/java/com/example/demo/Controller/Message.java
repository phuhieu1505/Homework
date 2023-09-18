package com.example.demo.Controller;

import com.example.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Message {
    @Autowired
    private MessageService messageService;
    @GetMapping("/hello")
    public String helloWorld(){
        return messageService.getMessage();
    }
    @PostMapping("/post")
    public String Post(){
        return "Hello Post";
    }


}
