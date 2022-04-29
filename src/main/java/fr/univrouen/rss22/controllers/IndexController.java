package fr.univrouen.rss22.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@GetMapping("/")
	public String acceuil() {
		return "acceuil";
    }  
	
	@GetMapping("/help")
	public String help() {
        return "help";
    }
}

