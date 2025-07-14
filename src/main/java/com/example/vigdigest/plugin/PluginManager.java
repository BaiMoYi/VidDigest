package com.example.vigdigest.plugin;

import com.example.vigdigest.monitor.PluginMetrics;
import io.micrometer.core.instrument.Timer;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Manages video plugins with hot load/unload capability.
 */
@Component
public class PluginManager {
    private static final Logger log = LoggerFactory.getLogger(PluginManager.class);
    private final Map<String, VideoPlugin> plugins = new HashMap<>();
    private final PluginMetrics metrics;
    public PluginManager(PluginMetrics metrics) {
        this.metrics = metrics;
    }

    /**
     * Load plugin jar from path.
     *
     * @param jarPath plugin jar path
     */
    public synchronized void load(Path jarPath) {
        if (!Files.exists(jarPath)) {
            throw new IllegalArgumentException("Plugin jar missing: " + jarPath);
        }
        try {
            URL url = jarPath.toUri().toURL();
            URLClassLoader loader = new URLClassLoader(new URL[]{url}, getClass().getClassLoader());
            ServiceLoader<VideoPlugin> found = ServiceLoader.load(VideoPlugin.class, loader);
            for (VideoPlugin plugin : found) {
                plugins.put(plugin.name(), plugin);
                log.info("Loaded plugin {}", plugin.name());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load plugin", e);
        }
    }

    /**
     * Unload plugin by name.
     *
     * @param name plugin name
     */
    public synchronized void unload(String name) {
        if (plugins.remove(name) != null) {
            log.info("Unloaded plugin {}", name);
        }
    }

    /**
     * List loaded plugins.
     *
     * @return plugin names
     */
    public Collection<String> list() {
        return Collections.unmodifiableCollection(plugins.keySet());
    }

    /**
     * Access loaded plugin by name.
     *
     * @param name plugin name
     * @return plugin or null
     */
    public VideoPlugin get(String name) {
        return plugins.get(name);
    }

    /**
     * Run plugin with metric recording.
     */
    public Mono<?> process(String name, FilePart file) {
        VideoPlugin plugin = plugins.get(name);
        if (plugin == null) {
            return Mono.error(new IllegalArgumentException("unknown plugin"));
        }
        Timer.Sample sample = Timer.start();
        return plugin.process(file)
            .doOnSuccess(r -> sample.stop(metrics.timer(name)));
    }
}
