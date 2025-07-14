package com.example.vigdigest.service;

import com.example.vigdigest.pipeline.ProcessingStage;
import com.example.vigdigest.pipeline.VideoContext;
import com.example.vigdigest.pipeline.VideoPipeline;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VideoServiceTest {

    @Test
    void processInvokesStages() {
        ProcessingStage stage = new ProcessingStage() {
            @Override public String name() { return "test"; }
            @Override public Mono<VideoContext> handle(VideoContext ctx) {
                ctx.data().put("called", true); return Mono.just(ctx);
            }
        };
        VideoPipeline pipeline = new VideoPipeline(List.of(stage));
        VideoService svc = new VideoService(pipeline);
        VideoContext ctx = svc.handle(new File("demo")).block();
        assertTrue((Boolean) ctx.data().get("called"));
    }

    @Test
    void processReturnsContext() {
        VideoPipeline pipeline = new VideoPipeline(List.of(ctx -> Mono.just(ctx)));
        VideoService svc = new VideoService(pipeline);
        assertNotNull(svc.handle(new File("demo")).block());
    }
}
