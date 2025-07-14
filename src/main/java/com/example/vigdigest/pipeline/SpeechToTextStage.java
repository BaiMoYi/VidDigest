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
 * Convert speech to text using STT engine.
 */
@Component
public class SpeechToTextStage implements ProcessingStage {
    private static final Logger log = LoggerFactory.getLogger(SpeechToTextStage.class);
    private final Semaphore semaphore = new Semaphore(1);

    @Override
    public String name() {
        return "speech-to-text";
    }

    @Override
    public Mono<VideoContext> handle(VideoContext ctx) {
        return Mono.fromCallable(() -> {
            semaphore.acquire();
            try {
                log.info("transcribe {}", ctx.file());
                // TODO: STT logic
                ctx.data().put("transcript", "");
                return ctx;
            } finally {
                semaphore.release();
            }
        }).subscribeOn(Schedulers.boundedElastic())
          .retryWhen(Retry.backoff(2, Duration.ofSeconds(1)));
    }
}
