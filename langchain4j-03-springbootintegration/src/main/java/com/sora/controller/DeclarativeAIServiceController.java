package com.sora.controller;

import com.sora.service.ChatAssistant;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeclarativeAIServiceController
{
    @Resource
    private ChatAssistant chatAssistantQwen;


    // http://localhost:9003/lc4j/boot/declarative
    @GetMapping(value = "/lc4j/boot/declarative")
    public String declarative(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt)
    {
        return chatAssistantQwen.chat(prompt);
    }
}