package com.alex.session.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Profile("!test")
@EnableWebSecurity
public class SecurityConfig {

}
