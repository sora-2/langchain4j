package com.sora.controller;

import com.sora.service.ChatAssistant;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HighApiController {

    @Resource
    private ChatAssistant chatAssistant;

    @GetMapping("/high/high")
    public String testHigh(@RequestParam String prompt){
        String chat = chatAssistant.chat(prompt);
        System.out.println(chat);
        return chat;
    }
}
