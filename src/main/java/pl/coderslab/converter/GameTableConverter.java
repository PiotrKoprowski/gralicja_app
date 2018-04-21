package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.gralicjaApp.entity.GameTable;
import pl.coderslab.gralicjaApp.repository.GameTableRepository;

public class GameTableConverter implements Converter<String, GameTable>{
	
	@Autowired
	GameTableRepository gameTableRepository;
	
	public GameTable convert(String id) {
		return gameTableRepository.findOne(Long.parseLong(id));
	}
}
