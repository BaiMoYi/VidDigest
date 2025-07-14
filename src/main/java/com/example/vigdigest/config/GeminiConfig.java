package com.example.vigdigest.config;

import org.springframework.ai.chat.model.google.GoogleGenerativeAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures Google Gemini chat model.
 */
@Configuration
public class GeminiConfig {

    /**
     * Creates Gemini chat model bean.
     *
     * @param apiKey Gemini API key
     * @return configured chat model
     */
    @Bean
    public GoogleGenerativeAiChatModel gemini(
            @Value("${GEMINI_API_KEY}") String apiKey) {
        return GoogleGenerativeAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-pro")
                .maxTokens(4096)
                .temperature(0.3f)
                .build();
    }
}
