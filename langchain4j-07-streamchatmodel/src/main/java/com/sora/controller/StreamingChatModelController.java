package com.sora.controller;

import com.sora.service.ChatAssistant;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamingChatModelController {

    @Resource
    private StreamingChatModel streamingChatModel;

    @Resource
    private ChatAssistant chatAssistant;

    @GetMapping("/chat1")
    public Flux<String> chat1(@RequestParam("prompt")String prompt){
        return Flux.create(emitter->{
            streamingChatModel.chat(prompt, new StreamingChatResponseHandler(){
                @Override
                public void onPartialResponse(String s) {
                    emitter.next(s);
                }

                @Override
                public void onCompleteResponse(ChatResponse chatResponse) {
                    emitter.complete();
                }

                @Override
                public void onError(Throwable throwable) {
                    emitter.error(throwable);
                }
            });

        });
    }

    @GetMapping(value = "/chatstream/chat2")
    public void chat2(@RequestParam(value = "prompt", defaultValue = "北京有什么好吃") String prompt)
    {
        System.out.println("---come in chat2");
        streamingChatModel.chat(prompt, new StreamingChatResponseHandler()
        {
            @Override
            public void onPartialResponse(String partialResponse)
            {
                System.out.println(partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse completeResponse)
            {
                System.out.println("---response over: "+completeResponse);
            }

            @Override
            public void onError(Throwable throwable)
            {
                throwable.printStackTrace();
            }
        });
    }



    @GetMapping(value = "/chatstream/chat3")
    public Flux<String> chat3(@RequestParam(value = "prompt", defaultValue = "南京有什么好吃") String prompt)
    {
        System.out.println("---come in chat3");

        return chatAssistant.chatFlux(prompt);
    }
}
