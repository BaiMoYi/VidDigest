package com.example.vigdigest.controller;

import com.example.vigdigest.service.ChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller exposing Gemini chat endpoint.
 */
@RestController
@RequestMapping("/api/v1/gemini")
public class GeminiController {

    private final ChatService chatService;

    public GeminiController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * POST /chat endpoint.
     *
     * @param payload request payload containing prompt
     * @return Gemini answer text
     */
    @PostMapping(value = "/chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String prompt = payload.getOrDefault("prompt", "");
        String answer = chatService.chat(prompt).getResult().getOutput();
        return Map.of("text", answer);
    }
}
