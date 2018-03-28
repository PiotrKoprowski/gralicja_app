package pl.coderslab.gralicjaApp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
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
import pl.coderslab.gralicjaApp.entity.GameTable;
import pl.coderslab.gralicjaApp.entity.User;
import pl.coderslab.gralicjaApp.repository.BoardGameRepository;
import pl.coderslab.gralicjaApp.repository.GameTableRepository;
import pl.coderslab.gralicjaApp.repository.UserRepository;

@Controller
@RequestMapping("/gameTables")
public class GameTableController {
	
	@Autowired
	GameTableRepository gameTableRepository;
	
	@Autowired
	BoardGameRepository boardGameRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@GetMapping("/add")
	public String add(Model m) {
		m.addAttribute("gameTable", new GameTable());
		return "gameTableForm";
	}
	
	@PostMapping("/add")
	public String addPost(@Valid @ModelAttribute GameTable gameTable, BindingResult br, HttpSession session) {
		if(br.hasErrors()) {
			return "gameTableForm";
		}
		User u = (User)session.getAttribute("loggedInUser");
		List<User> uList = new ArrayList<>();
		uList.add(u);
		gameTable.setUser(uList);
		gameTable.setActualNumOfPlayers(1);
		this.gameTableRepository.save(gameTable);
		return "redirect:/";
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
		List<String> hours = new ArrayList<>();
		hours.add("9:00");
		hours.add("9:15");
		hours.add("9:30");
		hours.add("9:45");
		hours.add("10:00");
		hours.add("10:15");
		hours.add("10:30");
		hours.add("10:45");
		hours.add("11:00");
		hours.add("11:15");
		hours.add("11:30");
		hours.add("11:45");
		hours.add("12:00");
		hours.add("12:15");
		hours.add("12:30");
		hours.add("12:45");
		hours.add("13:00");
		hours.add("13:15");
		hours.add("13:30");
		hours.add("13:45");
		hours.add("14:00");
		hours.add("14:15");
		hours.add("14:30");
		hours.add("14:45");
		hours.add("15:00");
		hours.add("15:15");
		hours.add("15:30");
		hours.add("15:45");
		hours.add("16:00");
		hours.add("16:15");
		hours.add("16:30");
		hours.add("16:45");
		hours.add("17:00");
		hours.add("17:15");
		hours.add("17:30");
		hours.add("17:45");
		hours.add("18:00");
		hours.add("18:15");
		hours.add("18:30");
		hours.add("18:45");
		return hours;
	}

}
