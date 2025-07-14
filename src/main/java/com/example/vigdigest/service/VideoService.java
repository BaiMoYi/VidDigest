package com.example.vigdigest.service;

import com.example.vigdigest.pipeline.VideoContext;
import com.example.vigdigest.pipeline.VideoPipeline;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;

/**
 * High level service for video processing workflow.
 */
@Service
public class VideoService {

    private final VideoPipeline pipeline;

    public VideoService(VideoPipeline pipeline) {
        this.pipeline = pipeline;
    }

    /**
     * Process given video file through pipeline.
     *
     * @param file video file
     * @return context with aggregated results
     */
    public Mono<VideoContext> handle(File file) {
        VideoContext ctx = new VideoContext().file(file);
        return pipeline.process(ctx);
    }
}
