package pl.coderslab.gralicjaApp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "table_reservation")
public class TableReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	private Date begin;

	@NotEmpty
	private Date end;

//	public static void main(String[] args) {
//		Calendar cal = Calendar.getInstance();
//		cal.set(2000, 1, 2, 9, 00, 00);
//		Date d = cal.getTime();
//
//		System.out.println(d);
//	}
//
	@ManyToOne
	private TableNumber tableNumber;
	
	@NotNull
	@ManyToMany(mappedBy = "tableReservations")
	private List<GameTable> gameTables = new ArrayList<>();

}