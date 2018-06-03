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
	
}
