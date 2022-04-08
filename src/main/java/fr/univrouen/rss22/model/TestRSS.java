package fr.univrouen.rss22.model;
 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

public class TestRSS {
	
	public String loadFileXML() {
		Resource resource = new DefaultResourceLoader().getResource("classpath:xml/item.xml");
		try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
	}

}
