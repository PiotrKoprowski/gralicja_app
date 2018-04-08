//package pl.coderslab.gralicjaApp.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import pl.coderslab.gralicjaApp.entity.GameTable;
//import pl.coderslab.gralicjaApp.repository.GameTableRepository;
//
//@RestController
//public class HomeRestController {
//	
//	@Autowired
//	GameTableRepository gameTableRepository;
//	
//	@GetMapping("")
//	public List<GameTable> getGameTableList() {
//		return this.gameTableRepository.findAll();
//	}
//	
//	@GetMapping("resources/css/style.css")
//	public String mistake(Model m) {
//		return "redirect:/";
//	}
//}