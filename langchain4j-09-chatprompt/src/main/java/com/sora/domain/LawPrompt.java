package com.sora.domain;

import dev.langchain4j.model.input.structured.StructuredPrompt;
import lombok.Data;


@Data
@StructuredPrompt("根据相关法律{{legal}},只能回答{{question}}问题")
public class LawPrompt {

    public String legal;
    public String question;

}
