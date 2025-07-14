package com.example.vigdigest.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Aggregate results from previous stages.
 */
@Component
public class ResultAggregationStage implements ProcessingStage {
    private static final Logger log = LoggerFactory.getLogger(ResultAggregationStage.class);

    @Override
    public String name() {
        return "result-aggregation";
    }

    @Override
    public Mono<VideoContext> handle(VideoContext ctx) {
        return Mono.fromCallable(() -> {
            log.info("aggregate results for {}", ctx.file());
            // TODO: aggregation logic
            return ctx;
        });
    }
}
