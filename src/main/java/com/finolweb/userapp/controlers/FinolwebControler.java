package com.finolweb.userapp.controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class FinolwebControler {
	
	@GetMapping
	public String helloword() {
		return "Hello Word";
	}

}
