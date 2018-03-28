package pl.coderslab.gralicjaApp.service;

import pl.coderslab.gralicjaApp.model.UserDTO;
import pl.coderslab.gralicjaApp.validator.EmailExistsException;
import pl.coderslab.gralicjaApp.entity.User;

public interface IUserService {
	User registerNewUserAccount(UserDTO accountDto) 
		      throws EmailExistsException;
}