package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.gralicjaApp.entity.BoardGame;
import pl.coderslab.gralicjaApp.repository.BoardGameRepository;

public class BoardGameConverter implements Converter<String, BoardGame>{
	
	@Autowired
	BoardGameRepository boardGameRepository;
	
	public BoardGame convert(String id) {
		return boardGameRepository.findOne(Long.parseLong(id));
	}
}
