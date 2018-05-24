package com.campusnum.reseausocialsb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class UserRestController {
	
	@GetMapping(path = "/users")
	public String showUsers() {
		return "Tous nos utilisateurs";
	}
	
	@GetMapping(path = "/user/{id}")
	public User showOneUser(@PathVariable Long id) {
		User user = new User();
		user.setId(id);
		user.setFirstName("toto");
		user.setLastName("Bateau");
		return user;
	}

}
