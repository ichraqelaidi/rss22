package fr.univrouen.rss22.controllers;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import fr.univrouen.rss22.model.Item;
import fr.univrouen.rss22.repository.ItemRepo;

@Controller
public class PostControllers {
	@Autowired
	ItemRepo repo;
	
	@PostMapping("/rss22/insert")
	  public String insertItem(@RequestParam(value = "xmlInput") String xmlInput,Model model) {
		if (validateXMLSchema()==true) {
			try {
	            JAXBContext context = JAXBContext.newInstance(Item.class);
	            Unmarshaller un = context.createUnmarshaller();
	            Document doc = convertStringToXMLDoc(xmlInput);
	            Item item1 = (Item) un.unmarshal(doc);
	            System.out.println("item: " + item1.getTitle() + " le " + item1.getDate());
	 	       	repo.save(item1);
	 	       	return "acceuil";
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
		}
	       	return "redirect:/rss22/resume/html";
	  }
	@PostMapping("/rss22/insert/file")
	  public String insertItemfile(@RequestParam("file") MultipartFile file,Model model) throws IOException {
		if (validateXMLSchema()==true) {
			try {
	            JAXBContext context = JAXBContext.newInstance(Item.class);
	            Unmarshaller un = context.createUnmarshaller();
	            Item item1 = (Item) un.unmarshal(convert(file));
	            System.out.println("item: " + item1.getTitle() + " le " + item1.getDate());
	 	       	repo.save(item1);
	 	       	return "acceuil";
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
	private static Document convertStringToXMLDoc(String strXMLValue) 
    {
      
        try
        {
        	//Create a new object of DocumentBuilderFactory
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            
        	//Create an object DocumentBuilder to parse the specified XML Data
            DocumentBuilder builder = dbfactory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(strXMLValue)));
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
	public static boolean validateXMLSchema(){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("src/main/resources/static/fileXSD.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("src/main/resources/static/test.xml")));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}
