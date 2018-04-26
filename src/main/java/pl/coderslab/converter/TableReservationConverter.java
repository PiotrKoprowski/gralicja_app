package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.gralicjaApp.entity.TableReservation;
import pl.coderslab.gralicjaApp.repository.TableReservationRepository;

public class TableReservationConverter implements Converter<String, TableReservation>{
	
	@Autowired
	TableReservationRepository tableReservationRepository;
	
	public TableReservation convert(String id) {
		return tableReservationRepository.findOne(Long.parseLong(id));
	}
}
