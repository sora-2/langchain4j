package com.sora.service;

import com.sora.domain.LawPrompt;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface LawAssistant {


    @SystemMessage("你只能回答编程相关的问题，若不是请直接返回：不好意思，我无法回答")
    @UserMessage("请回答以下问题{{question}},字数在{{num}}以内")
    String chat(@V("question")String question , @V("num")Integer num);

    @SystemMessage("你只能回答编程相关的问题，若不是请直接返回：不好意思，我无法回答")
    String chat(LawPrompt prompt);
}
