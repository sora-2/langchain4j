package com.sora.controller;

import com.sora.service.ChatAssistant;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.internal.chat.ToolMessage;
import dev.langchain4j.model.output.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Base64;

@RestController
public class ImageModelController {

    @Value("classpath:static/images/14.png")
    private Resource resource;

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private WanxImageModel wanxImageModel;

    @GetMapping("/imagecall")
    public String callImageAnalyse() throws IOException {

        byte[] contentAsByteArray = resource.getContentAsByteArray();
        String imageString = Base64.getEncoder().encodeToString(contentAsByteArray);
        UserMessage message = UserMessage.from(TextContent.from("简单介绍一下这张图"),ImageContent.from(imageString,"image/png"));
        ChatResponse chat = chatModel.chat(message);
        String text = chat.aiMessage().text();
        System.out.println(text);
        return text;
    }


    @GetMapping("/imageGenerate")
    public String callImageGenerate() throws IOException {

        Response<Image> generate = wanxImageModel.generate("杯子");
        System.out.println(generate.content().url());

        return generate.content().url().toString();
    }
}
