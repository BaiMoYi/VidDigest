package com.example.vigdigest.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Convert speech to text using STT engine.
 */
@Component
public class SpeechToTextStage implements ProcessingStage {
    private static final Logger log = LoggerFactory.getLogger(SpeechToTextStage.class);

    @Override
    public String name() {
        return "speech-to-text";
    }

    @Override
    public Mono<VideoContext> handle(VideoContext ctx) {
        return Mono.fromCallable(() -> {
            log.info("transcribe {}", ctx.file());
            // TODO: STT logic
            ctx.data().put("transcript", "");
            return ctx;
        });
    }
}
