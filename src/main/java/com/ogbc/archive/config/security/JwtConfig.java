package com.ogbc.archive.config.security;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtConfig
{
    @Autowired
    private Environment environment;

    @Bean
    public JwtDecoder jwtDecoder()
    {
        return NimbusJwtDecoder.withJwkSetUri(environment.getProperty("spring.security.oauth2.resourceserver.jwt.jwk-set-uri"))
                .jwtProcessorCustomizer(customizer -> {
                    customizer.setJWSTypeVerifier(new DefaultJOSEObjectTypeVerifier<>(new JOSEObjectType("at+jwt")));
                })
                .build();
    }
}