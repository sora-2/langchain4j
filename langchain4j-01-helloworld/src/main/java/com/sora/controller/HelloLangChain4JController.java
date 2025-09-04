package com.sora.controller;

import dev.langchain4j.model.chat.ChatModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloLangChain4JController
{
    @Resource
    private ChatModel chatModelQwen;

    // http://localhost:9001/langchain4j/hello?prompt=如何学习java
    @GetMapping(value = "/langchain4j/hello")
    public String hello(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt)
    {
        String result = chatModelQwen.chat(prompt);

        System.out.println("通过langchain4j调用模型返回结果：\n"+result);

        return result;
    }
}