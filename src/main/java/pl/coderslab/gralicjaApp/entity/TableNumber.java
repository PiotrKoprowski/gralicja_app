package pl.coderslab.gralicjaApp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "table_number")
public class TableNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private List<Date> begin = new ArrayList<>();

	private List<Date> end = new ArrayList<>();
	
	@OneToMany(mappedBy = "tableNumber")
	private List<TableReservation> tableReservations = new ArrayList<>();

	public TableNumber(List<Date> begin, List<Date> end, List<TableReservation> tableReservations) {
		super();
		this.begin = begin;
		this.end = end;
		this.tableReservations = tableReservations;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Date> getBegin() {
		return begin;
	}

	public void setBegin(List<Date> begin) {
		this.begin = begin;
	}

	public List<Date> getEnd() {
		return end;
	}

	public void setEnd(List<Date> end) {
		this.end = end;
	}

	public List<TableReservation> getTableReservations() {
		return tableReservations;
	}

	public void setTableReservations(List<TableReservation> tableReservations) {
		this.tableReservations = tableReservations;
	}

}
