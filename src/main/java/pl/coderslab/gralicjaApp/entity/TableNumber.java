package pl.coderslab.gralicjaApp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TableNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
    @ElementCollection(targetClass=LocalDateTime.class)
	private List<LocalDateTime> beginning;

	@Column
    @ElementCollection(targetClass=LocalDateTime.class)
	private List<LocalDateTime> ending;
	
	@OneToMany(mappedBy = "tableNumber", cascade = CascadeType.MERGE)
	    private List<TableReservation> tableReservation = new ArrayList<>();
	
	public TableNumber() {
		super();
	}

	public TableNumber(List<LocalDateTime> beginning, List<LocalDateTime> ending, List<TableReservation> tableReservation) {
		super();
		this.beginning = beginning;
		this.ending = ending;
		this.tableReservation = tableReservation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<LocalDateTime> getBeginning() {
		return beginning;
	}

	public void setBeginning(List<LocalDateTime> beginning) {
		this.beginning = beginning;
	}

	public List<LocalDateTime> getEnding() {
		return ending;
	}

	public void setEnding(List<LocalDateTime> ending) {
		this.ending = ending;
	}

	public List<TableReservation> getTableReservation() {
		return tableReservation;
	}

	public void setTableReservation(List<TableReservation> tableReservation) {
		this.tableReservation = tableReservation;
	}

}
