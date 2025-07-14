package com.example.vigdigest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.VaultContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.vigdigest.service.ChatService;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class ApplicationIntegrationTest {

    @Container
    static GenericContainer<?> redis = new GenericContainer<>("redis:7.2-alpine").withExposedPorts(6379);

    @Container
    static VaultContainer<?> vault = new VaultContainer<>("hashicorp/vault:1.15.2");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("REDIS_HOST", () -> redis.getHost());
        registry.add("REDIS_PORT", () -> redis.getMappedPort(6379));
        registry.add("VAULT_URI", () -> "http://" + vault.getHost() + ":" + vault.getFirstMappedPort());
        registry.add("VAULT_TOKEN", () -> "test");
    }

    @Autowired
    ChatService chatService;

    @Test
    void contextLoads() {
        assertNotNull(chatService);
    }
}
