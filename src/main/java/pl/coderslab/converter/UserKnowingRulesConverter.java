package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.gralicjaApp.entity.UserKnowingRules;
import pl.coderslab.gralicjaApp.repository.UserKnowingRulesRepository;

public class UserKnowingRulesConverter implements Converter<String, UserKnowingRules>{
	
	@Autowired
	UserKnowingRulesRepository userKnowingRulesRepository;
	
	public UserKnowingRules convert(String id) {
		return userKnowingRulesRepository.findOne(Long.parseLong(id));
	}
}
