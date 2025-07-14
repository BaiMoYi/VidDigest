package com.example.vigdigest.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Example preprocess stage.
 */
@Component
public class PreprocessStage implements ProcessingStage {
    private static final Logger log = LoggerFactory.getLogger(PreprocessStage.class);

    @Override
    public String name() {
        return "preprocess";
    }

    @Override
    public Mono<VideoContext> handle(VideoContext ctx) {
        return Mono.fromCallable(() -> {
            log.info("preprocess video {}", ctx.file());
            // TODO: real preprocess logic
            return ctx;
        });
    }
}
