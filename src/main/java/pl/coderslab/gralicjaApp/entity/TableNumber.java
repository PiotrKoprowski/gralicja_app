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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beginning == null) ? 0 : beginning.hashCode());
		result = prime * result + ((ending == null) ? 0 : ending.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((tableReservation == null) ? 0 : tableReservation.hashCode());
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
		TableNumber other = (TableNumber) obj;
		if (beginning == null) {
			if (other.beginning != null)
				return false;
		} else if (!beginning.equals(other.beginning))
			return false;
		if (ending == null) {
			if (other.ending != null)
				return false;
		} else if (!ending.equals(other.ending))
			return false;
		if (id != other.id)
			return false;
		if (tableReservation == null) {
			if (other.tableReservation != null)
				return false;
		} else if (!tableReservation.equals(other.tableReservation))
			return false;
		return true;
	}

	@Override
	public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("TableNumber [id=").append(id).append(", beginning=").append(beginning).append(", ending=").append(ending)
        		.append(", tableReservation=").append(tableReservation).append("]");
        return builder.toString();
	}

}
