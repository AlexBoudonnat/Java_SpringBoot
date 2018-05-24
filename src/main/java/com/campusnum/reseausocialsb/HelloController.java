package com.campusnum.reseausocialsb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	
	@RequestMapping(path="/hello")
	public String index() {
		return "Salutations de Spring Boot !!";
	}
}
