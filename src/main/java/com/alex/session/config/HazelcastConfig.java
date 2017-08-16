package com.alex.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.config.Config;

@Profile({ "spring-session", "hazelcast-wm" })
@Configuration
public class HazelcastConfig {

	@Bean
	@Conditional(HazelcastRequiredCondition.class)
	public Config config() {

		return new Config();
	}
}
