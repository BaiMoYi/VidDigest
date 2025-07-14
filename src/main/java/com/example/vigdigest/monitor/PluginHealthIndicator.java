package com.example.vigdigest.monitor;

import com.example.vigdigest.plugin.PluginManager;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Reports health status of loaded plugins.
 */
@Component
public class PluginHealthIndicator implements HealthIndicator {
    private final PluginManager pluginManager;

    public PluginHealthIndicator(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public Health health() {
        if (pluginManager.list().isEmpty()) {
            return Health.down().withDetail("plugins", "none").build();
        }
        return Health.up().withDetail("count", pluginManager.list().size()).build();
    }
}
