package fr.univrouen.rss22.controllers;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import fr.univrouen.rss22.model.Author;
import fr.univrouen.rss22.model.Feed;
import fr.univrouen.rss22.model.Item;
import fr.univrouen.rss22.repository.AuthorRepo;
import fr.univrouen.rss22.repository.FeedRepo;
import fr.univrouen.rss22.repository.ItemRepo;

@Controller
public class PostControllers {
	@Autowired
	ItemRepo repo;
	@Autowired
	FeedRepo feedRepo;
	
	@PostMapping(value="/rss22/insert", produces = MediaType.APPLICATION_XML_VALUE)
	  public String insertItem(@RequestParam(value = "xmlInput") String xmlInput,Model model) throws SAXException, IOException {
		if (validateXMLSchema(xmlInput)==true) {
			try {
	            JAXBContext context = JAXBContext.newInstance(Feed.class);
	            Unmarshaller un = context.createUnmarshaller();
	            Feed feed = (Feed) un.unmarshal(new StringReader(xmlInput));
	 	       	feedRepo.save(feed);
	 	       return "redirect:/rss22/resume/html";
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
		}
	       	return "redirect:/rss22/resume/html";
	  }
	@PostMapping(value="/rss22/insert/file", produces = MediaType.APPLICATION_XML_VALUE)
	  public String insertItemfile(@RequestParam("file") MultipartFile file,Model model) throws IOException {
		if (validateXMLSchema(convert(file))==true) {
			try {
	            JAXBContext context = JAXBContext.newInstance(Feed.class);
	            Unmarshaller un = context.createUnmarshaller();
	            Feed feed = (Feed) un.unmarshal(convert(file));
	            feedRepo.save(feed);
	 	       return "redirect:/rss22/resume/html";
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
		}
	       	return "redirect:/rss22/resume/html";
	  }
	public static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
	public static boolean validateXMLSchema(File file){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("src/main/resources/static/fileXSD.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
	public static boolean validateXMLSchema(String xml) throws SAXException, IOException{
        
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("src/main/resources/static/fileXSD.xsd"));
            Validator validator = schema.newValidator();
            StringReader reader = new StringReader(xml);
            validator.validate(new StreamSource(reader));
            return true;
    }
}
