package com.sora.config;

import com.sora.service.ChatAssistant;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    @Bean(name = "qwen")
    public ChatModel modelQwen(){
        return OpenAiChatModel
                .builder()
                .apiKey(System.getenv("aliQwen-api-key"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();

    }

    @Bean(name="Deepseek")
    public ChatModel chatModelDeepseek()
    {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("deepseek-api-key"))
                .modelName("deepseek-chat")
                .baseUrl("https://api.deepseek.com/v1")
                .build();
    }

    @Bean
    public ChatAssistant chatAssistant(@Qualifier("qwen") ChatModel chatModel){
        return AiServices.create(ChatAssistant.class,chatModel);
    }
}
