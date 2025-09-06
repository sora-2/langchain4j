package com.sora.config;

import com.sora.service.ChatPersistenceAssistant;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LLMConfig
{
    @Resource
    private RedisChatMemoryStore redisChatMemoryStore;

    @Bean
    public ChatModel chatModel()
    {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("aliQwen-api-key"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public ChatPersistenceAssistant chatPersistenceAssistant(ChatModel chatModel){
        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
                .chatMemoryStore(redisChatMemoryStore)
                .id(memoryId)
                .maxMessages(1000)
                .build();

        return AiServices.builder(ChatPersistenceAssistant.class)
                .chatModel(chatModel)
                .chatMemoryProvider(chatMemoryProvider)
                .build();
    }


}
