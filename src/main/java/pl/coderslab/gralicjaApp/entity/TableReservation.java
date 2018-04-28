package pl.coderslab.gralicjaApp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class TableReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	private Date begin;

	@NotEmpty
	private Date end;

	@ManyToOne
	private TableNumber tableNumber;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameTable_id")
    private GameTable gameTable;
	
	
	public TableReservation() {
		super();
	}

	public TableReservation(Date begin, Date end, TableNumber tableNumber, GameTable gameTable) {
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

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
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