package pl.coderslab.gralicjaApp.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.coderslab.gralicjaApp.entity.BoardGame;
import pl.coderslab.gralicjaApp.entity.GameTable;
import pl.coderslab.gralicjaApp.entity.TableNumber;
import pl.coderslab.gralicjaApp.entity.TableReservation;
import pl.coderslab.gralicjaApp.entity.User;
import pl.coderslab.gralicjaApp.repository.BoardGameRepository;
import pl.coderslab.gralicjaApp.repository.GameTableRepository;
import pl.coderslab.gralicjaApp.repository.TableNumberRepository;
import pl.coderslab.gralicjaApp.repository.TableReservationRepository;
import pl.coderslab.gralicjaApp.repository.UserRepository;

@Service
public class GameTableService {


	@Autowired
	GameTableRepository gameTableRepository;
	
	@Autowired
	BoardGameRepository boardGameRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TableReservationRepository tableReservationRepository;
	
	@Autowired
	TableNumberRepository tableNumberRepository;

	@PersistenceContext
	EntityManager entityManager;
	
	public String addGameTableGet(Model m, long boardGameId) {
		final GameTable gameTable = new GameTable();
		final BoardGame boardGame = boardGameRepository.findOne(boardGameId);
		m.addAttribute("gameTable", gameTable);
		m.addAttribute("boardGame", boardGame);
		return "gameTableForm";
	}
	
	public String addGameTable(GameTable gameTable, Model m, Principal principal, long boardGameId) {
		final String name = principal.getName();	
		final User u = userRepository.findByUsername(name);
		final boolean userKnowingRules = gameTable.isFamiliarWithGame();
		gameTable.getUsers().put(u, userKnowingRules);
		final BoardGame boardGame = boardGameRepository.findOne(boardGameId);
		gameTable.setBoardGame(boardGame);
		
		try {
			this.gameTableRepository.save(gameTable);
	    }
	    catch (RuntimeException e) {
	    	m.addAttribute("alert", "Stolik o takiej nazwie już istenieje podaj inną nazwę");
	    	return "gameTableForm";
	    }
		//table reservation part
		
		//data capture
		final String startingDayString = gameTable.getDay();
		int startingDay;
		if(startingDayString.equals("Sobota")) {
			startingDay = 1;
		} else {
			startingDay = 2;
		}
		
		final String startingHours = gameTable.getStartingHour();
		final String[] parts = startingHours.split(":");
		final int startingHour = Integer.parseInt(parts[0]);
		final int startingMin = Integer.parseInt(parts[1]);
		
		final int year = 2000;
		final int month = 1;
		final int day = startingDay;
		final Double endingHourDouble = Math.ceil(gameTable.getBoardGame().getGameLength() * 1.5); //multiply gameLenth and round up to full hour
		final int endingHour = startingHour + endingHourDouble.intValue(); //setting ending hour as a starting hour + rounded gameLenth
		final int endingMin = startingMin; //no need to change min if hour is rounding
		
		//creating reservation
		TableReservation reservation = new TableReservation();
		reservation.setGameTable(gameTable);
		
		
		LocalDateTime startingDate = LocalDateTime.of(year, month, day, startingHour, startingMin);
		reservation.setBegin(startingDate);
		
		LocalDateTime endingDate = LocalDateTime.of(year, month, day, endingHour, endingMin);
		reservation.setEnd(endingDate);
		this.tableReservationRepository.save(reservation);
		
		gameTable.setTableReservation(reservation);
		
		//finding table
		//first reservation when there is no table added
		if(tableNumberRepository.findOne((long) 1) == null) {
			TableNumber tableNumber = new TableNumber();
			
			List<LocalDateTime> beginning = new ArrayList<>();
			beginning.add(startingDate);
			tableNumber.setBeginning(beginning);
			List<LocalDateTime> ending = new ArrayList<>();
			ending.add(endingDate);
			tableNumber.setEnding(ending);
	
			tableNumber.getTableReservation().add(reservation);
			this.tableNumberRepository.save(tableNumber);
			
			reservation.setTableNumber(tableNumber);
			this.tableReservationRepository.save(reservation);
			
			gameTable.setNumberOfTable( (int) tableNumber.getId());
			this.gameTableRepository.save(gameTable);
			
		} else {
		//searching empty place to make reservation
		final long numbersOfTableToReservation = 3; //setting max number of tables which could be created
		boolean breakCondition = true;
		
			while(breakCondition) {
				for(long counter = 1; counter <= numbersOfTableToReservation + 1; counter++) {
					if(counter == numbersOfTableToReservation + 1) {
						this.tableReservationRepository.delete(reservation);
						this.gameTableRepository.delete(gameTable);
						m.addAttribute("alert", "Przepraszamy nie ma wolnych stolików w tym terminie, wybierz inny termin");
						return "gameTableForm";
					} else if(tableNumberRepository.findOne(counter) == null) {
						TableNumber tableNumber = new TableNumber();
						
						List<LocalDateTime> beginning = new ArrayList<>();
						beginning.add(startingDate);
						tableNumber.setBeginning(beginning);
						List<LocalDateTime> ending = new ArrayList<>();
						ending.add(endingDate);
						tableNumber.setEnding(ending);
	
						tableNumber.getTableReservation().add(reservation);
						this.tableNumberRepository.save(tableNumber);
						
						reservation.setTableNumber(tableNumber);
						this.tableReservationRepository.save(reservation);
						
						gameTable.setNumberOfTable( (int) tableNumber.getId());
						this.gameTableRepository.save(gameTable);
						breakCondition = false; //stop while loop
						break;
						
					} else {
						TableNumber tableNumber = tableNumberRepository.findOne(counter);
						List<LocalDateTime> beginning = tableNumber.getBeginning();
						List<LocalDateTime> ending = tableNumber.getEnding();
						
						// have to check all reservation before and after (in particular table) before making new reservation
						boolean beforeEvent = false; 
						boolean afterEvent = false;
					
						for(int i = 0; i < beginning.size(); i++) {
							if((beginning.get(i).compareTo(endingDate)>=0) && (beginning.get(i).compareTo(startingDate)>=0)) {
								beforeEvent = true;
								afterEvent = false;
							}else if((ending.get(i).compareTo(startingDate)<=0) && (ending.get(i).compareTo(endingDate)<=0)) {
								beforeEvent = false;
								afterEvent = true;
							}else {
								beforeEvent = false;
								afterEvent = false;
							}
						}
						
	//					if beforeEvent = true the loop will stop increase tableNumber
						if(beforeEvent) {
							beginning.add(startingDate);
							tableNumber.setBeginning(beginning);
							ending.add(endingDate);
							tableNumber.setEnding(ending);
	
							tableNumber.getTableReservation().add(reservation);
							this.tableNumberRepository.save(tableNumber);
							
							reservation.setTableNumber(tableNumber);
							this.tableReservationRepository.save(reservation);
							
							gameTable.setNumberOfTable( (int) tableNumber.getId());
							this.gameTableRepository.save(gameTable);
							breakCondition = false; //stop while loop
							break;
						}else if(afterEvent) {
							beginning.add(startingDate);
							tableNumber.setBeginning(beginning);
							ending.add(endingDate);
							tableNumber.setEnding(ending);
	
							tableNumber.getTableReservation().add(reservation);
							this.tableNumberRepository.save(tableNumber);
							
							reservation.setTableNumber(tableNumber);
							this.tableReservationRepository.save(reservation);
							
							gameTable.setNumberOfTable( (int) tableNumber.getId());
							this.gameTableRepository.save(gameTable);
							breakCondition = false; //stop while loop
							break;
						}else if(beginning.size() == 0) { //case when tableNumber was created but reservation was removed and it is created but empty
							beginning.add(startingDate);
							tableNumber.setBeginning(beginning);
							ending.add(endingDate);
							tableNumber.setEnding(ending);
	
							tableNumber.getTableReservation().add(reservation);
							this.tableNumberRepository.save(tableNumber);
							
							reservation.setTableNumber(tableNumber);
							this.tableReservationRepository.save(reservation);
							
							gameTable.setNumberOfTable( (int) tableNumber.getId());
							this.gameTableRepository.save(gameTable);
							breakCondition = false; //stop while loop
							break;
						}
					}
				}
			}
		}
		return "redirect:/";
	}
	
	public String addToTable(long tableId, String username, String rules) {
		GameTable gameTable = gameTableRepository.findOne(tableId);
		User u = userRepository.findByUsername(username);
		boolean userKnowingRules = Boolean.parseBoolean(rules);
		gameTable.getUsers().put(u, userKnowingRules);
		//setting familiar with game rules
	    if(!gameTable.isFamiliarWithGame() && userKnowingRules) {
	    	gameTable.setFamiliarWithGame(true);
	    }
	    this.gameTableRepository.save(gameTable);	
		return "redirect:/";
	}
	
	public String deleteFromTable(long tableId, String username) {
		GameTable gameTable = gameTableRepository.findOne(tableId);
		User u = userRepository.findByUsername(username);
		Map<User, Boolean> users = gameTable.getUsers();
		users.remove(u);
		//checking if in table is added player who knows the rules
		if(gameTable.isFamiliarWithGame() && !users.containsValue(true)) {
			gameTable.setFamiliarWithGame(false);
		}
		
		if(users.size()==0) {
			long tableNumberId = gameTable.getTableReservation().getTableNumber().getId(); //finding tableNumberId
			TableNumber tableNumber = this.tableNumberRepository.findOne(tableNumberId);
			List<LocalDateTime> beginning = tableNumber.getBeginning();
			beginning.remove(gameTable.getTableReservation().getBegin()); //removing beginning date from tableNumber
			tableNumber.setBeginning(beginning);
			
			List<LocalDateTime> ending = tableNumber.getEnding();
			ending.remove(gameTable.getTableReservation().getEnd()); //removing ending date from tableNumber
			tableNumber.setEnding(ending);
			
			List<TableReservation> tableReservation = tableNumber.getTableReservation();
			tableReservation.remove(gameTable.getTableReservation()); //removing reservation from tableNumber
			tableNumber.setTableReservation(tableReservation);
			

			
			this.tableNumberRepository.save(tableNumber);
			this.tableReservationRepository.delete(gameTable.getTableReservation());
			this.gameTableRepository.delete(gameTable);
		} else {
			this.gameTableRepository.save(gameTable);
		}
	    return "redirect:/";
	}
}
