package com.alex.session.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);

	private static final String ATTRIBUTE_NAME = "testAtt";

	@GetMapping
	public String testSession(@RequestParam(required = false) boolean shouldWrite,
			@RequestParam(required = false) boolean shouldWait, HttpSession session)
			throws InterruptedException {

		Object attribute = session.getAttribute(ATTRIBUTE_NAME);
		LOGGER.info("[READ]: {}", attribute);

		if (shouldWait) {
			Thread.sleep(3000);
		}

		if (shouldWrite) {
			long currentTimeMillis = System.currentTimeMillis();
			session.setAttribute(ATTRIBUTE_NAME, currentTimeMillis);

			LOGGER.info("[WRITE]: {}", currentTimeMillis);

			return String.valueOf(currentTimeMillis);
		} else {

			return String.valueOf(attribute);
		}
	}
}
