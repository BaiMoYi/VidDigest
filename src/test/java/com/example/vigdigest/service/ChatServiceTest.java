package com.example.vigdigest.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ai.chat.model.ChatRequest;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.google.GoogleGenerativeAiChatModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChatServiceTest {

    @Test
    void chatDelegatesToModel() {
        GoogleGenerativeAiChatModel model = mock(GoogleGenerativeAiChatModel.class);
        when(model.call(any(ChatRequest.class))).thenReturn(new ChatResponse("hi"));
        ChatService service = new ChatService(model);
        ChatResponse resp = service.chat("hi");
        verify(model, times(1)).call(any(ChatRequest.class));
        assertEquals("hi", resp.getResult().getOutput());
    }

    @Test
    void chatReturnsAnswer() {
        GoogleGenerativeAiChatModel model = mock(GoogleGenerativeAiChatModel.class);
        when(model.call(any(ChatRequest.class))).thenReturn(new ChatResponse("pong"));
        ChatService service = new ChatService(model);
        assertEquals("pong", service.chat("ping").getResult().getOutput());
    }
}
