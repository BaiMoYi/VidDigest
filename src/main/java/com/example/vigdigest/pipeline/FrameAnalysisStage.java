package com.example.vigdigest.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Analyze video frames.
 */
@Component
public class FrameAnalysisStage implements ProcessingStage {
    private static final Logger log = LoggerFactory.getLogger(FrameAnalysisStage.class);

    @Override
    public String name() {
        return "frame-analysis";
    }

    @Override
    public Mono<VideoContext> handle(VideoContext ctx) {
        return Mono.fromCallable(() -> {
            log.info("analyze frames of {}", ctx.file());
            // TODO: frame analysis logic
            return ctx;
        });
    }
}
