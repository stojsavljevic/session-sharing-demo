package com.alex.session.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("spring-session")
@Configuration
@PropertySource("classpath:spring-session.properties")
public class SessionSpringSessionConfig {

}
