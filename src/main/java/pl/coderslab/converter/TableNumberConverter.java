package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.gralicjaApp.entity.TableNumber;
import pl.coderslab.gralicjaApp.repository.TableNumberRepository;

public class TableNumberConverter implements Converter<String, TableNumber>{
	
	@Autowired
	TableNumberRepository tableNumberRepository;
	
	public TableNumber convert(String id) {
		return tableNumberRepository.findOne(Long.parseLong(id));
	}
}
