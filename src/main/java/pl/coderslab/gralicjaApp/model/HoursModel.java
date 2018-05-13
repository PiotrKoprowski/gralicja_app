package pl.coderslab.gralicjaApp.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class HoursModel {
	
	public List<String> getHours(){
		List<String> hours = new ArrayList<>();
		hours.add("9:00");
		hours.add("9:15");
		hours.add("9:30");
		hours.add("9:45");
		hours.add("10:00");
		hours.add("10:15");
		hours.add("10:30");
		hours.add("10:45");
		hours.add("11:00");
		hours.add("11:15");
		hours.add("11:30");
		hours.add("11:45");
		hours.add("12:00");
		hours.add("12:15");
		hours.add("12:30");
		hours.add("12:45");
		hours.add("13:00");
		hours.add("13:15");
		hours.add("13:30");
		hours.add("13:45");
		hours.add("14:00");
		hours.add("14:15");
		hours.add("14:30");
		hours.add("14:45");
		hours.add("15:00");
		hours.add("15:15");
		hours.add("15:30");
		hours.add("15:45");
		hours.add("16:00");
		hours.add("16:15");
		hours.add("16:30");
		hours.add("16:45");
		hours.add("17:00");
		hours.add("17:15");
		hours.add("17:30");
		hours.add("17:45");
		hours.add("18:00");
		hours.add("18:15");
		hours.add("18:30");
		hours.add("18:45");
		return hours;
	}
}
