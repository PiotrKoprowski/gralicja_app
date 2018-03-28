package pl.coderslab.gralicjaApp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.gralicjaApp.entity.BoardGame;
import pl.coderslab.gralicjaApp.repository.BoardGameRepository;

@Controller
@RequestMapping("/boardGames")
public class BoardGameController {
	
	@Autowired
	BoardGameRepository boardGameRepository;
	
	@GetMapping("/add")
	public String add(Model m) {
		m.addAttribute("boardGame", new BoardGame());
		return "boardGameForm";
	}
	
	@PostMapping("/add")
	public String addPost(@Valid @ModelAttribute BoardGame boardGame, BindingResult br) {
		if(br.hasErrors()) {
			return "boardGameForm";
		}
		this.boardGameRepository.save(boardGame);
		return "redirect:/";
	}
	
	@ModelAttribute("categories")
	public List<String> getCategories(){
		List<String> categories = new ArrayList<String>();
		categories.add("Edukacyjne");
		categories.add("Karty i gry karciane");
		categories.add("Logiczne");
		categories.add("Losowe");
		categories.add("Łamigłowki");
		categories.add("Planszowe");
		categories.add("Podróżne");
		categories.add("Przygodowe");
		categories.add("Rodzinne");
		categories.add("Strategiczne i ekonomiczne");
		categories.add("Towarzyskie");
		return categories;
	}

	@ModelAttribute("publishers")
	public List<String> getPublishers(){
		List<String> publishers = new ArrayList<String>();
		publishers.add("alexander");
		publishers.add("trefl");
		publishers.add("rebel");
		publishers.add("small foot design");
		publishers.add("tactic");
		publishers.add("goki");
		publishers.add("piatnik");
		publishers.add("adamigo");
		publishers.add("granna");
		publishers.add("g3");
		publishers.add("haba");
		publishers.add("egmont");
		publishers.add("eureka");
		publishers.add("cartamundi");
		publishers.add("hasbro gaming");
		publishers.add("galakta");
		publishers.add("hanayama");
		publishers.add("clementoni");
		publishers.add("cayro");
		publishers.add("foxgames");
		return publishers;
	}
}
