package com.example.vigdigest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import java.net.URI;

/**
 * Demonstrates pulling Gemini API key from Vault.
 */
@Configuration
public class VaultConfig {

    @Bean
    public VaultTemplate vaultTemplate(@Value("${vault.token}") String token,
                                       @Value("${vault.uri}") String uri) {
        VaultEndpoint endpoint = VaultEndpoint.from(URI.create(uri));
        return new VaultTemplate(endpoint, new TokenAuthentication(token));
    }
}
