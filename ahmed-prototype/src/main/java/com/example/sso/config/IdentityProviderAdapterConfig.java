package com.example.sso.config;

import com.example.sso.adapter.IdentityProviderAdapter;
import com.example.sso.adapter.IdentityProviderV1Adapter;
import com.example.sso.adapter.IdentityProviderV2Adapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IdentityProviderAdapterConfig {

    @Value("${sso.version}")
    private String ssoVersion;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IdentityProviderAdapter identityProviderAdapter(RestTemplate restTemplate) {
        if ("v2".equalsIgnoreCase(ssoVersion)) {
            return new IdentityProviderV2Adapter(restTemplate);
        } else {
            return new IdentityProviderV1Adapter(restTemplate);
        }
    }
}
