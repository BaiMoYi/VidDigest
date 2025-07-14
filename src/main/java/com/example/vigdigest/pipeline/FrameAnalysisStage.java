package com.example.vigdigest.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.Semaphore;

/**
 * Analyze video frames.
 */
@Component
public class FrameAnalysisStage implements ProcessingStage {
    private static final Logger log = LoggerFactory.getLogger(FrameAnalysisStage.class);
    private final Semaphore semaphore = new Semaphore(2);

    @Override
    public String name() {
        return "frame-analysis";
    }

    @Override
    public Mono<VideoContext> handle(VideoContext ctx) {
        return Mono.fromCallable(() -> {
            semaphore.acquire();
            try {
                log.info("analyze frames of {}", ctx.file());
                // TODO: frame analysis logic
                return ctx;
            } finally {
                semaphore.release();
            }
        }).subscribeOn(Schedulers.boundedElastic())
          .retryWhen(Retry.backoff(2, Duration.ofSeconds(1)));
    }
}
