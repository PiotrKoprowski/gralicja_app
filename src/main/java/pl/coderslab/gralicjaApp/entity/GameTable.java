package pl.coderslab.gralicjaApp.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
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

	private boolean familiarWithGame;

	@NotEmpty
	private String day;

	@NotEmpty
	private String startingHour;

	private int numberOfTable;
	
	//Boolean storage information if user knows game rules
	@ElementCollection
	@CollectionTable(joinColumns = @JoinColumn(name = "gameTable_id"))
	@MapKeyJoinColumn(name = "user_id")
    private Map<User, Boolean> users = new HashMap<>();
	
	@OneToOne(mappedBy = "gameTable",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private TableReservation tableReservation;
	
	public GameTable() {
		super();
	}

	public GameTable(String tableName, BoardGame boardGame, int maxNumOfPlayers, int currentNumOfPlayers,
			boolean familiarWithGame, String day, String startingHour, int numberOfTable, Map<User, Boolean> users,
			TableReservation tableReservation) {
		super();
		this.tableName = tableName;
		this.boardGame = boardGame;
		this.maxNumOfPlayers = maxNumOfPlayers;
		this.familiarWithGame = familiarWithGame;
		this.day = day;
		this.startingHour = startingHour;
		this.numberOfTable = numberOfTable;
		this.users = users;
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

	public Map<User, Boolean> getUsers() {
		return users;
	}

	public void setUsers(Map<User, Boolean> users) {
		this.users = users;
	}

	public TableReservation getTableReservation() {
		return tableReservation;
	}

	public void setTableReservation(TableReservation tableReservation) {
		this.tableReservation = tableReservation;
	}

}
