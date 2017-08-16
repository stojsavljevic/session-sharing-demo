package com.alex.session.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ConfigPrinter implements ApplicationListener<ApplicationContextEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPrinter.class);

	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();

			String implementation = null;
			String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
			for (int i = 0; i < activeProfiles.length; i++) {
				if ("hazelcast-wm".equals(activeProfiles[i])) {
					implementation = "hazelcast-wm";
					break;
				} else if ("spring-session".equals(activeProfiles[i])) {
					String property = applicationContext.getEnvironment().getProperty("spring.session.store-type");
					switch (property) {
						case "hazelcast":
							implementation = "spring-session: hazelcast";
							break;
						case "none":
							implementation = "HttpSession";
							break;
						case "redis":
							implementation = "spring-session: redis";
							break;
						case "hash-map":
							implementation = "spring-session: hash-map";
							break;
						default:
							implementation = "NOT RECOGNIZED";
							break;
					}
				}
			}

			LOGGER.info("::::::::::::::::::::::::::::::::::::::");
			LOGGER.info("Session type is {}", implementation);
			LOGGER.info("::::::::::::::::::::::::::::::::::::::");
		}
	}
}