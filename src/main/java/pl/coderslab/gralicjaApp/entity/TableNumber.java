//package pl.coderslab.gralicjaApp.entity;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "table_number")
//public class TableNumber {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//
//	private List<Date> begin = new ArrayList<>();
//
//	private List<Date> end = new ArrayList<>();
//	
//	@OneToMany(mappedBy = "tableNumber")
//	private List<TableReservation> tableReservations = new ArrayList<>();
//
//}
