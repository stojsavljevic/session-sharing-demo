package com.alex.session.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class HazelcastRequiredCondition implements Condition {
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		
		Environment env = context.getEnvironment();
		return null != env && ("hazelcast".equals(env.getProperty("spring.session.store-type"))
				|| env.getProperty("spring.profiles.active").contains("hazelcast"));
	}
}
