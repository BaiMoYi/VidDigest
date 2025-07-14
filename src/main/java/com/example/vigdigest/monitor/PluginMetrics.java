package com.example.vigdigest.monitor;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Records plugin processing times.
 */
@Component
public class PluginMetrics {
    private final MeterRegistry registry;
    private final Map<String, Timer> timers = new ConcurrentHashMap<>();

    public PluginMetrics(MeterRegistry registry) {
        this.registry = registry;
    }

    public Timer timer(String name) {
        return timers.computeIfAbsent(name,
            n -> Timer.builder("plugin.process.time").tag("plugin", n).register(registry));
    }
}
