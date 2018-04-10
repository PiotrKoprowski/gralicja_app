package pl.coderslab.gralicjaApp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.coderslab.gralicjaApp.entity.GameTable;
import pl.coderslab.gralicjaApp.entity.User;
import pl.coderslab.gralicjaApp.repository.GameTableRepository;
import pl.coderslab.gralicjaApp.repository.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	GameTableRepository gameTableRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("")
	public String home(Model m, Principal principal) {
	
//		String name = principal.getName();	
//		User u = userRepository.findByUsername(name);
//		List <GameTable> userGameTables = gameTableRepository.findByUsers(u);
//		m.addAttribute("userGameTables", userGameTables);
		List <GameTable> gameTables = gameTableRepository.findAll();
		
		m.addAttribute("gameTables", gameTables);

		return "home";
	}
	
	@GetMapping("resources/css/style.css")
	public String mistake(Model m) {
		return "redirect:/";
	}
}