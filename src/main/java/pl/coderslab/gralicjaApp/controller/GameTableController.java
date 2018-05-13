package pl.coderslab.gralicjaApp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import pl.coderslab.gralicjaApp.entity.BoardGame;
import pl.coderslab.gralicjaApp.entity.GameTable;
import pl.coderslab.gralicjaApp.model.HoursModel;
import pl.coderslab.gralicjaApp.repository.BoardGameRepository;
import pl.coderslab.gralicjaApp.service.GameTableService;

@Controller
@RequestMapping("/gameTables")
public class GameTableController {
	
	@Autowired
	BoardGameRepository boardGameRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	GameTableService gameTableService;
	
	@Autowired
	HoursModel hoursModel;
	
	@GetMapping("/add/{boardGameId}")
	public String add(Model m, @PathVariable long boardGameId) {
		return this.gameTableService.addGameTableGet(m, boardGameId);
	}
	
	@PostMapping("/add/{boardGameId}")
	public String addPost(@Valid @ModelAttribute GameTable gameTable, BindingResult br, Model m, Principal principal, @PathVariable long boardGameId) {
		if(br.hasErrors()) {
			return "gameTableForm";
		}
		return this.gameTableService.addGameTable(gameTable, m, principal, boardGameId);
	}
	
	@RequestMapping("/addToTable/{tableId}/{username}/{rules}")
	private String addToTable(@PathVariable long tableId, @PathVariable String username, @PathVariable String rules) {
		return this.gameTableService.addToTable(tableId, username, rules);
		}
	
	@RequestMapping("/deleteFromTable/{tableId}/{username}")
	private String deleteFromTable(@PathVariable long tableId,	@PathVariable String username) {
		return this.gameTableService.deleteFromTable(tableId, username);
		}
	
	@ModelAttribute("games")
	public List<BoardGame> getGames(){
		return boardGameRepository.findAll();
	}
	
	@ModelAttribute("days")
	public List<String> getDays(){
		List<String> days = new ArrayList<>();
		days.add("Sobota");
		days.add("Niedziela");
		return days;
	}
	
	@ModelAttribute("hours")
	public List<String> getHours(){
		return this.hoursModel.getHours();
	}

}
