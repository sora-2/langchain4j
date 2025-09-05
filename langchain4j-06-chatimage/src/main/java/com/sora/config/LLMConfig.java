package com.sora.config;

import com.sora.service.ChatAssistant;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    @Bean(name = "qwen")
    public ChatModel modelImage(){
        return OpenAiChatModel
                .builder()
                .apiKey(System.getenv("aliQwen-api-key"))
                //qwen-vl-max 是一个多模态大模型，支持图片和文本的结合输入，适用于视觉-语言任务。
                .modelName("qwen-vl-max")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();

    }

    @Bean(name = "wanx")
    public WanxImageModel wanxImageModel(){
        return WanxImageModel
                .builder()
                .apiKey(System.getenv("aliQwen-api-key"))
                .modelName("wanx2.1-t2i-turbo")
                .build();

    }

//    @Bean
//    public ChatAssistant chatAssistant(@Qualifier("qwen") ChatModel chatModel){
//        return AiServices.create(ChatAssistant.class,chatModel);
//    }
}
