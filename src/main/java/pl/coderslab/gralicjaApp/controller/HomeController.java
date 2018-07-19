package pl.coderslab.gralicjaApp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.coderslab.gralicjaApp.entity.GameTable;
import pl.coderslab.gralicjaApp.repository.BoardGameRepository;
import pl.coderslab.gralicjaApp.repository.GameTableRepository;
import pl.coderslab.gralicjaApp.repository.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	GameTableRepository gameTableRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BoardGameRepository boardGameReposiotory;
	
	@GetMapping("")
	public String home(Model m, Principal principal) {		
		List <GameTable> gameTables = gameTableRepository.findAll();
		m.addAttribute("gameTables", gameTables);
		return "home";
	}
	
	@GetMapping("rest/")
	public String homeRest() {		
		return "homeRest";
	}
	
	@GetMapping("resources/css/style.css")
	public String mistake(Model m) {
		return "redirect:/";
	}
}