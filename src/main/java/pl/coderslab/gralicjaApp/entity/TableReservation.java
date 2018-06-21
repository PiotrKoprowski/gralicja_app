package pl.coderslab.gralicjaApp.entity;



import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class TableReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private LocalDateTime begin;

	@NotNull
	private LocalDateTime end;

	@ManyToOne
	private TableNumber tableNumber;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameTable_id")
    private GameTable gameTable;
	
	
	public TableReservation() {
		super();
	}

	public TableReservation(LocalDateTime begin, LocalDateTime end, TableNumber tableNumber, GameTable gameTable) {
		super();
		this.begin = begin;
		this.end = end;
		this.tableNumber = tableNumber;
		this.gameTable = gameTable;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public void setBegin(LocalDateTime begin) {
		this.begin = begin;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public TableNumber getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(TableNumber tableNumber) {
		this.tableNumber = tableNumber;
	}

	public GameTable getGameTable() {
		return gameTable;
	}

	public void setGameTable(GameTable gameTable) {
		this.gameTable = gameTable;
	}

}