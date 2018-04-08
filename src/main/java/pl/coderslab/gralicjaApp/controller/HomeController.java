package pl.coderslab.gralicjaApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.coderslab.gralicjaApp.entity.GameTable;
import pl.coderslab.gralicjaApp.entity.User;
import pl.coderslab.gralicjaApp.repository.GameTableRepository;

@Controller
public class HomeController {
	
	@Autowired
	GameTableRepository gameTableRepository;
	
	@GetMapping("")
	public String home(Model m) {
		List <GameTable> gameTables = gameTableRepository.findAll();
		m.addAttribute("gameTables", gameTables);
		return "home";
	}
	
	@GetMapping("resources/css/style.css")
	public String mistake(Model m) {
		return "redirect:/";
	}
}