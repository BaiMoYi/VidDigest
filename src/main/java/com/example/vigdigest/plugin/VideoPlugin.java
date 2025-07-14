package com.example.vigdigest.plugin;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

/**
 * Video plugin interface for extensible processing.
 */
public interface VideoPlugin {
    /**
     * Plugin unique name.
     *
     * @return name
     */
    String name();

    /**
     * Process uploaded video file asynchronously.
     *
     * @param file video part
     * @return result object
     */
    Mono<?> process(FilePart file);
}
