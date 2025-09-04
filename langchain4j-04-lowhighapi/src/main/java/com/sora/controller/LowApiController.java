package com.sora.controller;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.TokenUsage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LowApiController
{
    @Resource(name = "qwen")
    private ChatModel chatModelQwen;

    @Resource(name = "deepseek")
    private ChatModel chatModelDeepSeek;

    @GetMapping(value = "/lowapi/api01")
    public String api01(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt)
    {
        String result = chatModelQwen.chat(prompt);

        System.out.println("通过langchain4j调用模型返回结果："+result);

        return result;
    }

    // http://localhost:9004/lowapi/api02
    @GetMapping(value = "/lowapi/api02")
    public String api02(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt)
    {
        ChatResponse chatResponse = chatModelDeepSeek.chat(UserMessage.from(prompt));

        String result = chatResponse.aiMessage().text();
        System.out.println("通过langchain4j调用模型返回结果："+result);

        TokenUsage tokenUsage = chatResponse.tokenUsage();
        System.out.println("本次调用消耗Token："+tokenUsage);

        result = result +"\t\n"+ tokenUsage;

        return result;
    }

}
