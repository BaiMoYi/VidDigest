package com.example.vigdigest.controller;

import com.example.vigdigest.plugin.PluginManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.Map;

/**
 * REST endpoints to manage plugins.
 */
@RestController
@RequestMapping("/api/v1/plugins")
public class PluginController {

    private final PluginManager pluginManager;

    public PluginController(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @PostMapping("/load")
    public ResponseEntity<?> load(@RequestParam String path) {
        pluginManager.load(Path.of(path));
        return ResponseEntity.ok(Map.of("status", "loaded"));
    }

    @PostMapping("/unload")
    public ResponseEntity<?> unload(@RequestParam String name) {
        pluginManager.unload(name);
        return ResponseEntity.ok(Map.of("status", "unloaded"));
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(pluginManager.list());
    }
}
