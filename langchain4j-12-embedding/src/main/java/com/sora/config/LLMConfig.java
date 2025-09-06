package com.sora.config;


import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig
{
    @Bean
    public EmbeddingModel chatModel()
    {
        return OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("aliQwen-api-key"))
                .modelName("text-embedding-v3")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    /**
     * 创建Qdrant客户端
     * @return
     */
    @Bean
    public QdrantClient qdrantClient() {
        QdrantGrpcClient.Builder grpcClientBuilder =
                QdrantGrpcClient.newBuilder("192.168.150.102", 6334, false);
        return new QdrantClient(grpcClientBuilder.build());
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return QdrantEmbeddingStore.builder()
                .host("192.168.150.102")
                .port(6334)
                .collectionName("test-qdrant")
                .build();
    }


}