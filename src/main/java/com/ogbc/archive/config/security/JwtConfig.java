package com.ogbc.archive.config.security;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@PropertySource(value={"classpath:application.yml"})
public class JwtConfig
{
    //@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    //private String jwkSetUri;

    @Bean
    public JwtDecoder jwtDecoder()
    {
        return NimbusJwtDecoder.withJwkSetUri("https://api.asgardeo.io/t/jvdw/oauth2/jwks")
                .jwtProcessorCustomizer(customizer -> {
                    customizer.setJWSTypeVerifier(new DefaultJOSEObjectTypeVerifier<>(new JOSEObjectType("at+jwt")));
                })
                .build();
    }
}