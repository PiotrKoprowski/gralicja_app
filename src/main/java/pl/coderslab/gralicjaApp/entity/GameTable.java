package pl.coderslab.gralicjaApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class GameTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Column(unique=true)
	private String tableName;

	@ManyToOne
    @JoinColumn(name = "boardGame_id")
	private BoardGame boardGame;

	@NotNull
	private int maxNumOfPlayers;

	@NotNull
	private int actualNumOfPlayers;

	private boolean familiarWithGame;

	@NotEmpty
	private String day;

	@NotNull
	private String startingHour;

	private int numberOfTable;
	
	
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch=FetchType.EAGER)
    private List<User> users = new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name="id_userKnowingRules")
	private List<UserKnowingRules> userKnowingRules = new ArrayList<>();
	
	@OneToOne(mappedBy = "gameTable",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private TableReservation tableReservation;
	
	public GameTable() {
		super();
	}

	public GameTable(String tableName, BoardGame boardGame, int maxNumOfPlayers, int actualNumOfPlayers,
			boolean familiarWithGame, String day, String startingHour, int numberOfTable, List<User> users,
			List<UserKnowingRules> userKnowingRules, TableReservation tableReservation) {
		super();
		this.tableName = tableName;
		this.boardGame = boardGame;
		this.maxNumOfPlayers = maxNumOfPlayers;
		this.actualNumOfPlayers = actualNumOfPlayers;
		this.familiarWithGame = familiarWithGame;
		this.day = day;
		this.startingHour = startingHour;
		this.numberOfTable = numberOfTable;
		this.users = users;
		this.userKnowingRules = userKnowingRules;
		this.tableReservation = tableReservation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	public int getMaxNumOfPlayers() {
		return maxNumOfPlayers;
	}

	public void setMaxNumOfPlayers(int maxNumOfPlayers) {
		this.maxNumOfPlayers = maxNumOfPlayers;
	}

	public int getActualNumOfPlayers() {
		return actualNumOfPlayers;
	}

	public void setActualNumOfPlayers(int actualNumOfPlayers) {
		this.actualNumOfPlayers = actualNumOfPlayers;
	}

	public boolean isFamiliarWithGame() {
		return familiarWithGame;
	}

	public void setFamiliarWithGame(boolean familiarWithGame) {
		this.familiarWithGame = familiarWithGame;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartingHour() {
		return startingHour;
	}

	public void setStartingHour(String startingHour) {
		this.startingHour = startingHour;
	}

	public int getTable() {
		return numberOfTable;
	}

	public void setTable(int numberOfTable) {
		this.numberOfTable = numberOfTable;
	}
	
	public int getNumberOfTable() {
		return numberOfTable;
	}

	public void setNumberOfTable(int numberOfTable) {
		this.numberOfTable = numberOfTable;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<UserKnowingRules> getUserKnowingRules() {
		return userKnowingRules;
	}

	public void setUserKnowingRules(List<UserKnowingRules> userKnowingRules) {
		this.userKnowingRules = userKnowingRules;
	}

	public TableReservation getTableReservation() {
		return tableReservation;
	}

	public void setTableReservation(TableReservation tableReservation) {
		this.tableReservation = tableReservation;
	}

}
