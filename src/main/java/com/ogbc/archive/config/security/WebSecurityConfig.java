package com.ogbc.archive.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig
{
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception
    {
        return http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(configurer ->
                        configurer
                                .antMatchers("/", "/content", "/content/topic/**", "/content/passage/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                // Enable JWT Authentication
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }
}
