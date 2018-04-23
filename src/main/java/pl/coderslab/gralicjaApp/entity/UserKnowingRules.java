package pl.coderslab.gralicjaApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserKnowingRules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean knowingRules;


    public UserKnowingRules(User user, boolean knowingRules) {
		super();
		this.user = user;
		this.knowingRules = knowingRules;
	}

	public UserKnowingRules() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isKnowingRules() {
		return knowingRules;
	}

	public void setKnowingRules(boolean knowingRules) {
		this.knowingRules = knowingRules;
	}

}