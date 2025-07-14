package com.example.vigdigest.service;

import org.springframework.ai.chat.model.ChatRequest;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.google.GoogleGenerativeAiChatModel;
import org.springframework.stereotype.Service;

/**
 * Wraps Gemini chat operations.
 */
@Service
public class ChatService {

    private final GoogleGenerativeAiChatModel chatModel;

    public ChatService(GoogleGenerativeAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * Sends prompt to Gemini model.
     *
     * @param prompt input text
     * @return Gemini response
     */
    public ChatResponse chat(String prompt) {
        ChatRequest request = new ChatRequest(prompt);
        return chatModel.call(request);
    }
}
