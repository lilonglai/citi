package com.example.messagingstompwebsocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {


	@GetMapping("/api")
	public Greeting greeting() throws Exception {
		Thread.sleep(1000); // simulated delay
		long startTime = System.nanoTime();
		return new Greeting("Hello, " + "kevin!", startTime);
	}

}
