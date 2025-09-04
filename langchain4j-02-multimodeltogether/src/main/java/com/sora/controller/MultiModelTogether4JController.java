package com.sora.controller;

import dev.langchain4j.model.chat.ChatModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MultiModelTogether4JController
{
    @Resource(name = "Qwen")
    private ChatModel chatModelQwen;
    @Resource(name = "Deepseek")
    private ChatModel chatModelDeepseek;

    // http://localhost:9001/langchain4j/Qwen?prompt=如何学习java
    @GetMapping(value = "/langchain4j/Qwen")
    public String Qwen(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt)
    {
        String result = chatModelQwen.chat(prompt);

        System.out.println("通过langchain4j调用Qwen模型返回结果：\n"+result);

        return result;
    }

    // http://localhost:9001/langchain4j/deepseek?prompt=如何学习java
    @GetMapping(value = "/langchain4j/deepseek")
    public String deepseek(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt)
    {
        String result = chatModelDeepseek.chat(prompt);

        System.out.println("通过langchain4j调用deepseek模型返回结果：\n"+result);

        return result;
    }
}