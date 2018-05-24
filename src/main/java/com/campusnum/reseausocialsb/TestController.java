package com.campusnum.reseausocialsb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/test")
	public String testMethod () {
		return "Hello World";
	}
    
    @GetMapping("/newuser")
    public User newUser() {
    	User user = new User();
    	user.setFirstName("Alex");
    	user.setLastName("Boud");
		return user;
    }

	
}
