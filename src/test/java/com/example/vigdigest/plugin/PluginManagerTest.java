package com.example.vigdigest.plugin;

import com.example.vigdigest.monitor.PluginMetrics;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PluginManagerTest {

    @Test
    void loadAndUnloadPlugin() throws Exception {
        Path dir = Files.createTempDirectory("plugin");
        Path src = dir.resolve("TestPlugin.java");
        try (FileWriter w = new FileWriter(src.toFile())) {
            w.write("package t; public class TestPlugin implements com.example.vigdigest.plugin.VideoPlugin {"+
                    "public String name(){return \"test\";}"+
                    "public reactor.core.publisher.Mono<?> process(org.springframework.http.codec.multipart.FilePart f){return reactor.core.publisher.Mono.empty();}}" );
        }
        javax.tools.JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler();
        assertEquals(0, compiler.run(null, null, null, src.toString()));
        Path classes = dir.resolve("classes");
        Files.createDirectory(classes);
        Files.move(dir.resolve("TestPlugin.class"), classes.resolve("TestPlugin.class"));
        Path services = classes.resolve("META-INF/services");
        Files.createDirectories(services);
        Files.writeString(services.resolve("com.example.vigdigest.plugin.VideoPlugin"), "t.TestPlugin");
        Path jar = dir.resolve("plugin.jar");
        try (java.util.jar.JarOutputStream jos = new java.util.jar.JarOutputStream(Files.newOutputStream(jar))) {
            jos.putNextEntry(new java.util.jar.JarEntry("TestPlugin.class"));
            jos.write(Files.readAllBytes(classes.resolve("TestPlugin.class")));
            jos.closeEntry();
            jos.putNextEntry(new java.util.jar.JarEntry("META-INF/services/com.example.vigdigest.plugin.VideoPlugin"));
            jos.write("t.TestPlugin".getBytes());
            jos.closeEntry();
        }
        PluginManager manager = new PluginManager(new PluginMetrics(new io.micrometer.core.instrument.simple.SimpleMeterRegistry()));
        manager.load(jar);
        assertTrue(manager.list().contains("test"));
        manager.unload("test");
        assertFalse(manager.list().contains("test"));
    }

    @Test
    void listInitiallyEmpty() {
        PluginManager manager = new PluginManager(new PluginMetrics(new io.micrometer.core.instrument.simple.SimpleMeterRegistry()));
        assertTrue(manager.list().isEmpty());
    }
}
