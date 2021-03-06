package pl.coderslab.gralicjaApp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import pl.coderslab.gralicjaApp.validator.PasswordMatches;

@PasswordMatches
public class UserDTO {
    @NotNull
    @NotEmpty
    private String username;
	
    @NotNull
    @NotEmpty
    @Size(min=6)
    private String password;
    private String matchingPassword;
     
    @NotNull
    @NotEmpty
    private String email;

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
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}