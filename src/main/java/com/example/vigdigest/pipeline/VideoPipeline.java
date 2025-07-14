package com.example.vigdigest.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Coordinates execution of video processing stages.
 */
@Service
public class VideoPipeline {
    private static final Logger log = LoggerFactory.getLogger(VideoPipeline.class);
    private final List<ProcessingStage> stages;

    public VideoPipeline(List<ProcessingStage> stages) {
        this.stages = stages;
    }

    /**
     * Execute enabled stages sequentially.
     */
    public Mono<VideoContext> process(VideoContext ctx) {
        Mono<VideoContext> flow = Mono.just(ctx);
        for (ProcessingStage stage : stages) {
            flow = flow.flatMap(stage::handle)
                .doOnNext(c -> log.debug("stage {} done", stage.name()));
        }
        return flow;
    }
}
