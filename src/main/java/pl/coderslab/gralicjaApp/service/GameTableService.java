package pl.coderslab.gralicjaApp.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import pl.coderslab.gralicjaApp.entity.UserKnowingRules;
import pl.coderslab.gralicjaApp.repository.BoardGameRepository;
import pl.coderslab.gralicjaApp.repository.GameTableRepository;
import pl.coderslab.gralicjaApp.repository.TableNumberRepository;
import pl.coderslab.gralicjaApp.repository.TableReservationRepository;
import pl.coderslab.gralicjaApp.repository.UserKnowingRulesRepository;
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
	UserKnowingRulesRepository userKnowingRulesRepository;
	
	@Autowired
	TableReservationRepository tableReservationRepository;
	
	@Autowired
	TableNumberRepository tableNumberRepository;

	@PersistenceContext
	EntityManager entityManager;
	
	public String addGameTable(GameTable gameTable, Model m, Principal principal, long boardGameId) {
	String name = principal.getName();	
	User u = userRepository.findByUsername(name);
	gameTable.getUsers().add(u);
	gameTable.setActualNumOfPlayers(1);
	boolean knowingRules = gameTable.isFamiliarWithGame();
	UserKnowingRules userKR = new UserKnowingRules(u, knowingRules);
	this.userKnowingRulesRepository.save(userKR);
	gameTable.getUserKnowingRules().add(userKR);
	BoardGame boardGame = boardGameRepository.findOne(boardGameId);
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
	String startingDayString = gameTable.getDay();
	int startingDay;
	if(startingDayString.equals("Sobota")) {
		startingDay = 1;
	} else {
		startingDay = 2;
	}
	
	String startingHours = gameTable.getStartingHour();
	String[] parts = startingHours.split(":");
	int startingHour = Integer.parseInt(parts[0]);
	int startingMin = Integer.parseInt(parts[1]);
	
	int year = 2000;
	int month = 0;
	int day = startingDay;
	Double endingHourDouble = Math.ceil(gameTable.getBoardGame().getGameLength() * 1.5); //multiply gameLenth and round up to full hour
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
	Date endingDate = endingCal.getTime();
	reservation.setEnd(endingDate);
	this.tableReservationRepository.save(reservation);
	
	gameTable.setTableReservation(reservation);
	
	//finding table
	//first reservation when there is no table added
	if(tableNumberRepository.findOne((long) 1) == null) {
		TableNumber tableNumber = new TableNumber();
		
		List<Date> beginning = new ArrayList<>();
		beginning.add(startingDate);
		tableNumber.setBeginning(beginning);
		List<Date> ending = new ArrayList<>();
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
	long numbersOfTableToReservation = 3; //setting max number of tables which could be created
	boolean breakCondition = true;
	
		while(breakCondition) {
			for(long counter = 1; counter <= numbersOfTableToReservation + 1; counter++) {
				if(counter == numbersOfTableToReservation + 1) {
					this.tableReservationRepository.delete(reservation);
					this.gameTableRepository.delete(gameTable);
					this.userKnowingRulesRepository.delete(userKR);
					m.addAttribute("alert", "Przepraszamy nie ma wolnych stolików w tym terminie, wybierz inny termin");
					return "gameTableForm";
				} else if(tableNumberRepository.findOne(counter) == null) {
					TableNumber tableNumber = new TableNumber();
					
					List<Date> beginning = new ArrayList<>();
					beginning.add(startingDate);
					tableNumber.setBeginning(beginning);
					List<Date> ending = new ArrayList<>();
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
					List<Date> beginning = tableNumber.getBeginning();
					List<Date> ending = tableNumber.getEnding();
					
					
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
}
