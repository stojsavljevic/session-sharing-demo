package com.alex.session;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestUtil {

	@Autowired
	TestRestTemplate restTemplate;

	String cookie = null;

	static final String URL = "http://abc:8080/?shouldWrite=%s&shouldWait=%s";

	@Async
	public Future<String> writeValue() {

		return execute(true, true);
	}

	public String readValueWithWait() throws InterruptedException, ExecutionException {

		Future<String> readValue = execute(false, true);
		return readValue.get();
	}

	public String readValueNoWait() throws InterruptedException, ExecutionException {

		Future<String> readValue = execute(false, false);
		return readValue.get();
	}

	private Future<String> execute(boolean write, boolean wait) {

		if (cookie == null) {
			this.cookie = getCookie();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", cookie);

		String formatURL = String.format(URL, write, wait);

		ResponseEntity<String> response = this.restTemplate.exchange(formatURL,
				HttpMethod.GET,
				new HttpEntity<String>(headers), String.class);

		return new AsyncResult(response.getBody());
	}

	private String getCookie() {

		String formatURL = String.format(URL, false, false);

		ResponseEntity<Void> response = new RestTemplate().getForEntity(formatURL, Void.class);

		HttpHeaders headers = response.getHeaders();

		return headers.getFirst(headers.SET_COOKIE);
	}
}
