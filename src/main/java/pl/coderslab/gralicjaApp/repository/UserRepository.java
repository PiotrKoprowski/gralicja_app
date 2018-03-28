package pl.coderslab.gralicjaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.gralicjaApp.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findOneByEmail(String email);

}
