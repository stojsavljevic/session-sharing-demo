package com.alex.session;

import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@EnableAsync
@TestPropertySource(locations = "classpath:test.properties")
public class SessionDemoApplicationTests {

	@Autowired
	RequestUtil requestUtil;

	@Test
	public void testRaceConditions() throws Exception {

		// write a value but don't wait for request to finishes
		Future<String> writeValue = requestUtil.writeValue();

		// wait for a second
		Thread.sleep(1000);

		// writing is not finished yet and we fire another request that
		// basically does nothing but overwrites a session (in case of Hz)
		requestUtil.readValueWithWait();

		// both requests are done - go and read the value of session attribute
		String readValue = requestUtil.readValueNoWait();

		// compare
		Assert.assertEquals("FAILURE", writeValue.get(), readValue);
	}

}
