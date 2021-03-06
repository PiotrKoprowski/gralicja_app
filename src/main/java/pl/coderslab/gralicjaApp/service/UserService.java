package pl.coderslab.gralicjaApp.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.gralicjaApp.entity.User;
import pl.coderslab.gralicjaApp.model.UserDTO;
import pl.coderslab.gralicjaApp.repository.UserRepository;
import pl.coderslab.gralicjaApp.validator.EmailExistsException;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository repository;

	@Transactional
    public User registerNewUserAccount(UserDTO accountDto) 
      throws EmailExistsException {
         
        if (emailExist(accountDto.getEmail())) {   
            throw new EmailExistsException(
              "There is an account with that email address: " + accountDto.getEmail());
        }
        User user = new User();    
        user.setUsername(accountDto.getUsername());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList("ROLE_ADMIN"));
        return repository.save(user);       
    }

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}
}