package com.alex.session.config;

import java.util.Properties;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.web.SessionListener;
import com.hazelcast.web.WebFilter;
import com.hazelcast.web.spring.SpringAwareWebFilter;

@Profile("hazelcast-wm")
@Configuration
public class SessionHazelcastWMConfig {

	public WebFilter webFilter(HazelcastInstance hazelcastInstance) {

		Properties properties = new Properties();
		properties.put("instance-name", hazelcastInstance.getName());
		properties.put("map-name", "sessions");
		properties.put("sticky-session", "false");

		return new SpringAwareWebFilter(properties);
	}

	@Bean
	public FilterRegistrationBean someFilterRegistration(HazelcastInstance hazelcastInstance) {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(webFilter(hazelcastInstance));
		registration.addUrlPatterns("/*");
		registration.setOrder(Integer.MIN_VALUE);
		return registration;
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {

		return new SessionRegistryImpl();
	}

	@Bean
	public ServletListenerRegistrationBean<SessionListener> hazelcastSessionListener() {

		return new ServletListenerRegistrationBean<>(new SessionListener());
	}
}
