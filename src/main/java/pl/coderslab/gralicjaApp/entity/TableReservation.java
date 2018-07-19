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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((gameTable == null) ? 0 : gameTable.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((tableNumber == null) ? 0 : tableNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableReservation other = (TableReservation) obj;
		if (begin == null) {
			if (other.begin != null)
				return false;
		} else if (!begin.equals(other.begin))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (gameTable == null) {
			if (other.gameTable != null)
				return false;
		} else if (!gameTable.equals(other.gameTable))
			return false;
		if (id != other.id)
			return false;
		if (tableNumber == null) {
			if (other.tableNumber != null)
				return false;
		} else if (!tableNumber.equals(other.tableNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
	        final StringBuilder builder = new StringBuilder();
	        builder.append("TableReservation [id=").append(id)
	        .append(", begin=").append(begin)
	        .append(", end=").append(end)
	        .append(", tableNumber=").append(tableNumber)
	        .append(", gameTable=").append(gameTable).append("]");
	        return builder.toString();
	}

//	@Override
//	public String toString() {
//        final StringBuilder builder = new StringBuilder();
//        builder.append("TableReservation [id=").append(id)
//        .append(", begin.day=").append(begin.getDayOfMonth())
//        .append(", begin.hour=").append(begin.getHour())
//        .append(", begin.minute=").append(begin.getMinute())
//        .append(", end.day=").append(end.getDayOfMonth())
//        .append(", end.hour=").append(end.getHour())
//        .append(", end.minute=").append(end.getMinute())
//        .append(", tableNumber=").append(tableNumber)
//        .append(", gameTable=").append(gameTable).append("]");
//        return builder.toString();
//	}
	
	
	
}