package com.campusnum.reseausocialsb;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Cette classe est un controller
@RequestMapping(path = "/demo")
public class MainController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/add") // Ajout d'un utilisateur
	public @ResponseBody String addNewUser(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String userName, @RequestParam String town, @RequestParam String age) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setTown(town);
		user.setAge(age);
		userRepository.save(user);
		return "Utilisateur sauvegardé";
	}

	@GetMapping(path = "/all") // Retourne tous les utilisateurs
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/user/{id}") // Retourne un utilisateur grace à son Id
	public @ResponseBody Optional<User> getUser(@PathVariable(value = "id") Long id) {
		return userRepository.findById(id);
	}

	@GetMapping(path = "/update/{id}") // Update un utilisateur grace à son Id
	public @ResponseBody String updateUser(@PathVariable(value = "id") Long id, @RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String userName, @RequestParam String town, @RequestParam String age) {
		Optional<User> getUser = userRepository.findById(id);
		User user = new User();
		if (getUser.isPresent()) {
			user = getUser.get();
			if (!user.getFirstName().equals(firstName)) {
				user.setFirstName(firstName);
			}
			if (!user.getLastName().equals(lastName)) {
				user.setLastName(lastName);
			}
			if (!user.getUserName().equals(userName)) {
				user.setUserName(userName);
			}
			if (!user.getTown().equals(town)) {
				user.setTown(town);
			}
			if (!user.getAge().equals(age)) {
				user.setAge(age);
			}
			userRepository.save(user);
		}
		return ("Utilisateur " + user.getFirstName() + "  " + user.getLastName() + " mis à jour.");
	}

	@GetMapping(path = "/delete/{id}") // Supprime un utilisateur grace à son Id
	public @ResponseBody String deleteUser(@PathVariable(value = "id") Long id) {
		userRepository.deleteById(id);
		return ("Utilisateur n° " + id + " supprimé.");
	}
}
