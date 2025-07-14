package com.example.vigdigest.pipeline;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Processing context shared across pipeline stages.
 */
public class VideoContext {
    private final Map<String, Object> data = new HashMap<>();
    private File file;

    public VideoContext file(File f) {
        this.file = f;
        return this;
    }

    public File file() {
        return file;
    }

    public Map<String, Object> data() {
        return data;
    }
}
