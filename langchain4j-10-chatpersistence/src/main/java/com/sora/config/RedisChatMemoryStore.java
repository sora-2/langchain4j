package com.sora.config;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisChatMemoryStore implements ChatMemoryStore {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public static final String CHAT_PREFIX="CHAT_MEMORY:";

    @Override
    public List<ChatMessage> getMessages(Object o) {
        String string = stringRedisTemplate.opsForValue().get(CHAT_PREFIX + o);
        return ChatMessageDeserializer.messagesFromJson(string);
    }

    @Override
    public void updateMessages(Object o, List<ChatMessage> list) {
        stringRedisTemplate.opsForValue().set(CHAT_PREFIX+o, ChatMessageSerializer.messagesToJson(list));
    }

    @Override
    public void deleteMessages(Object o) {
        stringRedisTemplate.delete(CHAT_PREFIX+o);
    }
}
