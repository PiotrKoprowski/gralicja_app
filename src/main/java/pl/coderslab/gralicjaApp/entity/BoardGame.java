package pl.coderslab.gralicjaApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class BoardGame {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String category;
	
	@NotEmpty
	private String publisher;
	
	@Min(2)
	@Max(30)
	private int minNumOfPlayers;
	
	@Min(2)
	@Max(30)
	private int maxNumOfPlayers;
	
	@NotNull
	private int minPlayerAge;

	private int maxPlayerAge;
	
	@NotNull
	private double gameLength;

	public BoardGame() {
		super();
	}

	public BoardGame(String title, String description, String category, String publisher, int minNumOfPlayers,
			int maxNumOfPlayers, int minPlayerAge, int maxPlayerAge, double gameLength) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.publisher = publisher;
		this.minNumOfPlayers = minNumOfPlayers;
		this.maxNumOfPlayers = maxNumOfPlayers;
		this.minPlayerAge = minPlayerAge;
		this.maxPlayerAge = maxPlayerAge;
		this.gameLength = gameLength;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getMinNumOfPlayers() {
		return minNumOfPlayers;
	}

	public void setMinNumOfPlayers(int minNumOfPlayers) {
		this.minNumOfPlayers = minNumOfPlayers;
	}
	
	public int getMaxNumOfPlayers() {
		return maxNumOfPlayers;
	}

	public void setMaxNumOfPlayers(int maxNumOfPlayers) {
		this.maxNumOfPlayers = maxNumOfPlayers;
	}

	public double getGameLength() {
		return gameLength;
	}

	public void setGameLength(double gameLength) {
		this.gameLength = gameLength;
	}

	public int getMinPlayerAge() {
		return minPlayerAge;
	}

	public void setMinPlayerAge(int minPlayerAge) {
		this.minPlayerAge = minPlayerAge;
	}

	public int getMaxPlayerAge() {
		return maxPlayerAge;
	}

	public void setMaxPlayerAge(int maxPlayerAge) {
		this.maxPlayerAge = maxPlayerAge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gameLength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + maxNumOfPlayers;
		result = prime * result + maxPlayerAge;
		result = prime * result + minNumOfPlayers;
		result = prime * result + minPlayerAge;
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		BoardGame other = (BoardGame) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(gameLength) != Double.doubleToLongBits(other.gameLength))
			return false;
		if (id != other.id)
			return false;
		if (maxNumOfPlayers != other.maxNumOfPlayers)
			return false;
		if (maxPlayerAge != other.maxPlayerAge)
			return false;
		if (minNumOfPlayers != other.minNumOfPlayers)
			return false;
		if (minPlayerAge != other.minPlayerAge)
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BoardGame [id=").append(id).append(", title=").append(title).append(", description=").append(description).append(", category=").append(category)
               .append(", publisher=").append(publisher).append(", minNumOfPlayers=").append(minNumOfPlayers).append(", maxNumOfPlayers=").append(maxNumOfPlayers)
               .append(", minPlayerAge=").append(minPlayerAge).append(", maxPlayerAge=").append(maxPlayerAge).append(", gameLength=").append(gameLength).append("]");
        return builder.toString();
	}
	
}
