package fr.univrouen.rss22.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "author")
public class Author {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@XmlAttribute
	private long id;
	@XmlElement
	private String name;
	@XmlElement
	private String email;
	@XmlElement
	private String uri;
    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;
	public Author() {
		
	}
	public Author(long id, String name, String email, String uri) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.uri = uri;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	

}
