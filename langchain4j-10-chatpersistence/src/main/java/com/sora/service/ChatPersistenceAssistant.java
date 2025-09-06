package com.sora.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

public interface ChatPersistenceAssistant
{
    /**
     * 聊天
     *
     * @param userId  用户 ID
     * @param message 消息
     * @return {@link String }
     */
    String chat(@MemoryId Long userId, @UserMessage String message);
}