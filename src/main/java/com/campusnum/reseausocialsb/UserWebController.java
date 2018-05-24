package com.campusnum.reseausocialsb;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(path="/web")
public class UserWebController implements WebMvcConfigurer  {
	@Autowired
	private UserRepository userRepository;

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/userlist").setViewName("userlist");
    }
	
	@GetMapping(path="/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
	
	@GetMapping("/addUser") // Affiche le formulaire pour créer un utilisateur
    public String addUserForm(Model model) {
		User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }
	
	@PostMapping(path = "/addUser") // Ajout d'un utilisateur
	public String addNewUser(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addUser";
		}
		userRepository.save(user);
		return "redirect:/web/user/"+user.getId();
	}
	
	@GetMapping(path = "/userlist") // Retourne un json de tous les utilisateurs
	public String getAllUsers(Model model) {
		Iterable<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "userList";
	}
	
	@GetMapping(path = "/user/{id}") // Retourne un json d'un utilisateur grace à son Id
	public String getUser(@PathVariable(value = "id") Long id, Model model) {
		Optional<User> optionalUser = userRepository.findById(id);
		User user;
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
			model.addAttribute("user", user);
		}
		
		return "userProfile";
	}
	
	@GetMapping("/updateuser/{id}") // Affiche le formulaire pour modifier un utilisateur
    public String updateUserForm(@PathVariable(value = "id") Long id, Model model) {
		Optional<User> optionalUser = userRepository.findById(id);
		User user;
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
			model.addAttribute("user", user);
		}
        return "updateUser";
    }
	
	@PostMapping(path = "/updateuser") // Update d'un utilisateur
	public String updateUser(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "updateUser";
		}	
		userRepository.save(user);
		return "redirect:/web/userlist";
	}

	@GetMapping(path = "/deleteuser/{id}") // Supprime un utilisateur grace à son Id
	public String deleteUser(@PathVariable(value = "id") Long id) {
		userRepository.deleteById(id);
		return "redirect:/web/userlist";
	}
	
}
