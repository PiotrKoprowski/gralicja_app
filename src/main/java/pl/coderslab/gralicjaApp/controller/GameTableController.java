package pl.coderslab.gralicjaApp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.gralicjaApp.entity.BoardGame;
import pl.coderslab.gralicjaApp.entity.GameTable;
import pl.coderslab.gralicjaApp.entity.TableNumber;
import pl.coderslab.gralicjaApp.entity.TableReservation;
import pl.coderslab.gralicjaApp.entity.User;
import pl.coderslab.gralicjaApp.entity.UserKnowingRules;
import pl.coderslab.gralicjaApp.repository.BoardGameRepository;
import pl.coderslab.gralicjaApp.repository.GameTableRepository;
import pl.coderslab.gralicjaApp.repository.TableNumberRepository;
import pl.coderslab.gralicjaApp.repository.TableReservationRepository;
import pl.coderslab.gralicjaApp.repository.UserKnowingRulesRepository;
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
	
	@Autowired
	UserKnowingRulesRepository userKnowingRulesRepository;
	
	@Autowired
	TableReservationRepository tableReservationRepository;
	
	@Autowired
	TableNumberRepository tableNumberRepository;

	@PersistenceContext
	EntityManager entityManager;
	
	@GetMapping("/add")
	public String add(Model m) {
		m.addAttribute("gameTable", new GameTable());
		return "gameTableForm";
	}
	
	@PostMapping("/add")
	public String addPost(@Valid @ModelAttribute GameTable gameTable, BindingResult br, HttpSession session, Principal principal) {
		if(br.hasErrors()) {
			return "gameTableForm";
		}
		String name = principal.getName();	
		User u = userRepository.findByUsername(name);
		gameTable.getUsers().add(u);
		gameTable.setActualNumOfPlayers(1);
		boolean knowingRules = gameTable.isFamiliarWithGame();
		UserKnowingRules userKR = new UserKnowingRules(u, knowingRules);
		this.userKnowingRulesRepository.save(userKR);
		gameTable.getUserKnowingRules().add(userKR);
		this.gameTableRepository.save(gameTable);
		
		//table resevation part
		
		//data capture
		String startingDayString = gameTable.getDay();
		int startingDay;
		if(startingDayString.equals("Sobota")) {
			startingDay = 0;
		} else {
			startingDay = 1;
		}
		
		String startingHours = gameTable.getStartingHour();
		String[] parts = startingHours.split(":");
		int startingHour = Integer.parseInt(parts[0]);
		int startingMin = Integer.parseInt(parts[1]);
		
		int year = 2000;
		int month = 0;
		int day = startingDay;
		Double endingHourDouble = Math.ceil(gameTable.getBoardGame().getGameLength()); //round up gameLength to full hour
		int endingHour = startingHour + endingHourDouble.intValue(); //setting ending hour as a starting hour + rounded gameLenth
		int endingMin = startingMin; //no need to change min if hour is rounding
		
		//creating reservation
		TableReservation reservation = new TableReservation();
		reservation.setGameTable(gameTable);
		
		Calendar startingCal = Calendar.getInstance();
		startingCal.set(year, month, day, startingHour, startingMin, 00);
		Date startingDate = startingCal.getTime();
		reservation.setBegin(startingDate);
		
		Calendar endingCal = Calendar.getInstance();
		endingCal.set(year, month, day, endingHour, endingMin, 00);
		Date endingDate = startingCal.getTime();
		reservation.setEnd(endingDate);
//		this.tableReservationRepository.save(reservation);
		
		//finding table
		//first reservation when there is no table added
		if(tableNumberRepository.findOne((long) 0) == null) {
			TableNumber tableNumber = new TableNumber();
			tableNumber.setId(0);
			tableNumber.getBeginning().add(startingDate);
			tableNumber.getEnding().add(endingDate);
			tableNumber.getTableReservation().add(reservation);
			this.tableNumberRepository.save(tableNumber);
		} else {
		//searching empty place to make reservation
		long numbersOfTableToReservation = 5; //setting max number of tables which could be created
		boolean breakCondition = true;
		
			while(breakCondition) {
				for(long counter = 0; counter < numbersOfTableToReservation; counter++) {
					if(tableNumberRepository.findOne(counter) == null) {
						TableNumber tableNumber = new TableNumber();
						tableNumber.setId(0);
						tableNumber.getBeginning().add(startingDate);
						tableNumber.getEnding().add(endingDate);
						tableNumber.getTableReservation().add(reservation);
						this.tableNumberRepository.save(tableNumber);
					} else {
					TableNumber tableNumber = tableNumberRepository.findOne(counter);
					List<Date> beginnig = tableNumber.getBeginning();
					List<Date> ending = tableNumber.getEnding();
					
					boolean beforeEvent = false;
					for(int i = 0; i < beginnig.size(); i++) {
						if((beginnig.get(i).compareTo(endingDate)>0) && (beginnig.get(i).compareTo(startingDate)>0)) {
							System.out.println("Nowe wydarzenie przed wydarzeniem");
							beforeEvent = true;
						}else {
							beforeEvent = false;
						}
					}
					//if beforeEvent = true the loop will stop increase tableNumber;
					if(beforeEvent) {
						break;
					}
					}
				
				}
			}
		}
		
		
		
//		int year = 2000;
//		int month = 0;
//		
//		Calendar cal = Calendar.getInstance();
//		int startingDay = 1;
//		int startingHour = 12;
//		int startingMin = 15;
//		cal.set(year, month, startingDay, startingHour, startingMin, 00);
//		Date startingDate = cal.getTime();
//		
//		Calendar cal2 = Calendar.getInstance();
//		int endingDay = 1;
//		int endingHour = 15;
//		int endingMin = 15;
//		cal2.set(year, month, endingDay, endingHour, endingMin, 00);
//		Date endingDate = cal2.getTime();
//		
//		Calendar calNewStarting = Calendar.getInstance();
//		int startingDayNewStarting = 1;
//		int startingHourNewStarting = 17;
//		int startingMinNewStarting = 15;
//		calNewStarting.set(year, month, startingDayNewStarting, startingHourNewStarting, startingMinNewStarting, 00);
//		Date startingDateNewStarting = calNewStarting.getTime();
//		
//		Calendar cal2NewEnding = Calendar.getInstance();
//		int endingDayNewEnding = 1;
//		int endingHourNewEnding = 20;
//		int endingMinNewEnding = 15;
//		cal2NewEnding.set(year, month, endingDayNewEnding, endingHourNewEnding, endingMinNewEnding, 00);
//		Date endingDateNewEnding = cal2NewEnding.getTime();
//		
//		if((startingDate.compareTo(endingDateNewEnding)>0) && (startingDate.compareTo(startingDateNewStarting)>0)) {
//			System.out.println("Nowe wydarzenie przed wydarzeniem");
//		}else if((endingDate.compareTo(startingDateNewStarting)<0) && (endingDate.compareTo(endingDateNewEnding)<0)) {
//			System.out.println("Nowe wydarzeenie po wydarzeniu");
//		}else {
//			System.out.println("brak miejsca - nastepny stolik");
//			// TODO increase date
//		}
//		
//		
//		public static void main(String[] args) {
//			
//			int year = 2000;
//			int month = 0;
//			int day = 1;
//			int min = 00;
//			
//			Calendar cal = Calendar.getInstance();
//			int startingHour = 12;
//			cal.set(year, month, day, startingHour, min, 00);
//			Date startingDate = cal.getTime();
//			
//			Calendar cal2 = Calendar.getInstance();
//			int endingHour = 15;
//			cal2.set(year, month, day, endingHour, min, 00);
//			Date endingDate = cal2.getTime();
//			
//			Calendar calNewStarting = Calendar.getInstance();
//			int startingHourNewStarting = 16;
//			calNewStarting.set(year, month, day, startingHourNewStarting, min, 00);
//			Date startingDateNewStarting = calNewStarting.getTime();
//			
//			Calendar cal2NewEnding = Calendar.getInstance();
//			int endingHourNewEnding = 17;
//			cal2NewEnding.set(year, month, day, endingHourNewEnding, min, 00);
//			Date endingDateNewEnding = cal2NewEnding.getTime();
//			
//			
//			if((startingDate.compareTo(endingDateNewEnding)>0) && (startingDate.compareTo(startingDateNewStarting)>0)) {
//				System.out.println("Nowe wydarzenie przed wydarzeniem");
//			}else if((endingDate.compareTo(startingDateNewStarting)<0) && (endingDate.compareTo(endingDateNewEnding)<0)) {
//				System.out.println("Nowe wydarzeenie po wydarzeniu");
//			}else {
//				System.out.println("brak miejsca - nastepny stolik");
//				// TODO increase date
//			}
//			
//		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/addToTable/{tableId}/{username}/{rules}")
	private String addToTable(@PathVariable long tableId,	@PathVariable String username, @PathVariable String rules,  HttpSession session) {
		GameTable gameTable = gameTableRepository.findOne(tableId);
		User u = userRepository.findByUsername(username);
		gameTable.getUsers().add(u);
		gameTable.setActualNumOfPlayers(gameTable.getUsers().size());
		boolean knowingRules = Boolean.parseBoolean(rules);
		UserKnowingRules userKR = new UserKnowingRules(u, knowingRules);
		this.userKnowingRulesRepository.save(userKR);
		gameTable.getUserKnowingRules().add(userKR);
		this.gameTableRepository.save(gameTable);	
	    return "redirect:/";
	}
	
	@RequestMapping("/deleteFromTable/{tableId}/{username}")
	private String deleteFromTable(@PathVariable long tableId,	@PathVariable String username) {
		GameTable gameTable = gameTableRepository.findOne(tableId);
		User u = userRepository.findByUsername(username);
		gameTable.getUsers().remove(u);
		// finding userKnowingRules id
		long userKnowingRulesId = 0;
		List<UserKnowingRules> usersKnowingRulesList = gameTable.getUserKnowingRules();
		for (UserKnowingRules userKnowingRules : usersKnowingRulesList) {
			if(userKnowingRules.getUser().getUsername().equals(username)) {
				userKnowingRulesId = userKnowingRules.getId();
				gameTable.getUserKnowingRules().remove(userKnowingRules);
			}
		}	
		if(gameTable.getUsers().size()==0) {
			this.gameTableRepository.delete(gameTable);
		} else {
			gameTable.setActualNumOfPlayers(gameTable.getUsers().size());
			this.gameTableRepository.save(gameTable);
		}
		this.userKnowingRulesRepository.delete(this.userKnowingRulesRepository.findOne(userKnowingRulesId));
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
