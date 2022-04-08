package fr.univrouen.rss22.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.rss22.model.Item;

@RestController
public class GetController {
	@GetMapping("/resume")
	public String getListRSSinXML() {
		return "Envoi de la liste des flux RSS";
	}
	@GetMapping("/id")
	public String getRSSinXML() {
		return "Détail du contenu RSS demandé";
	}
	
	@GetMapping("/guid")
	public String getRSSinXML(@RequestParam(value = "guid") String texte) {
		return ("Détail du flux RSS demandé : " + texte);
	}
	@GetMapping("/test")
	public String getTestinXML(@RequestParam(value = "nb") String nb,@RequestParam(value = "search") String search) {
		return ("Test : guid =" + nb+ " titre = "+search);
	}
	
	@RequestMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Item getXML() {
		Item it = new Item("12345678","Test item","2022-05-01T11:22:33");
		return it;
	}
}
