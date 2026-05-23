package com.school.config;

import com.school.security.AdminProperties;
import com.school.security.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AdminProperties.class, JwtProperties.class})
public class AppConfig {
}
