package com.sora.config;

import com.sora.service.LawAssistant;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    @Bean
    public ChatModel chatModel(){
        return OpenAiChatModel.builder()
                .apiKey("aliQwen-api-key")
                .modelName("qwen-long")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public LawAssistant chatAssistant(ChatModel chatModel){
        return AiServices.create(LawAssistant.class,chatModel);
    }
}
