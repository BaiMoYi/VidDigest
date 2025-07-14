package com.example.vigdigest.pipeline;

import reactor.core.publisher.Mono;

/**
 * Single stage in video processing pipeline.
 */
public interface ProcessingStage {
    /**
     * Name of the stage.
     */
    String name();

    /**
     * Execute stage logic.
     *
     * @param ctx processing context
     * @return updated context
     */
    Mono<VideoContext> handle(VideoContext ctx);
}
