package com.sora.config;

import com.sora.service.ChatAssistant;
import com.sora.service.ChatMemoryAssistant;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.TokenCountEstimator;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiTokenCountEstimator;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    @Bean
    public StreamingChatModel chatModel(){
        return OpenAiStreamingChatModel.builder()
                .apiKey("aliQwen-api-key")
                .modelName("qwen-long")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public ChatAssistant chatAssistant(StreamingChatModel streamingChatModel){
        return AiServices.create(ChatAssistant.class,streamingChatModel);
    }


    @Bean(name = "chatMessageWindowChatMemory")
    public ChatMemoryAssistant chatWithMessage(ChatModel chatModel){
        return AiServices.builder(ChatMemoryAssistant.class)
                .chatModel(chatModel)
                .chatMemoryProvider(id -> MessageWindowChatMemory.withMaxMessages(100))
                .build();
    }

    @Bean(name = "chatTokenWindowChatMemory")
    public ChatMemoryAssistant chatWithToken(ChatModel chatModel){
        TokenCountEstimator tokenCountEstimator = new OpenAiTokenCountEstimator("gpt-4");
        return AiServices.builder(ChatMemoryAssistant.class)
                .chatModel(chatModel)
                .chatMemoryProvider(id -> TokenWindowChatMemory.withMaxTokens(1000,tokenCountEstimator))
                .build();
    }

}
