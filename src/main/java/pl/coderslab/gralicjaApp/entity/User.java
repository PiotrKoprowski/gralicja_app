package pl.coderslab.gralicjaApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(unique=true)
	private String username;
	
	@NotEmpty
	private String password;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@ManyToMany(mappedBy = "users")
    private List<GameTable> gameTables = new ArrayList<GameTable>();
	
	public User() {
	}
	
	public User(String username, String password, String email) {
		super();
		this.username = username;
		setPassword(password);
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPasswordCorrect(String password) {
		return BCrypt.checkpw(password, this.password);
	}

	public List<GameTable> getGameTables() {
		return gameTables;
	}

	public void setGameTables(List<GameTable> gameTables) {
		this.gameTables = gameTables;
	}
	
}
