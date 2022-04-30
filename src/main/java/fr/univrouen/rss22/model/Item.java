package fr.univrouen.rss22.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "item")
public class Item {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@XmlAttribute
	private long guid;
	@XmlElement
	private String title;
	@XmlElement
	private String published;
	@XmlElement
	private String category;
	@XmlElement
	private String image;
	@XmlElement
	private String content;
	@XmlElementWrapper(name = "authors")
    @XmlElement(name = "author")
	@OneToMany(cascade = CascadeType.ALL)
    private Set<Author> autors;
	@ManyToOne
    @JoinColumn(name = "feed_id")
    Feed feed;
	
	public Feed getFeed() {
		return feed;
	}
	public void setFeed(Feed feed) {
		this.feed = feed;
	}
	public long getGuid() {
		return guid;
	}
	public void setGuid(long guid) {
		this.guid = guid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public Set<Author> getAutors() {
		return autors;
	}
	public void setAutors(Set<Author> autors) {
		this.autors = autors;
	}
	
	public Item(long guid, String title, String published, String category, String image, String content,
			Set<Author> autors) {
		super();
		this.guid = guid;
		this.title = title;
		this.published = published;
		this.category = category;
		this.image = image;
		this.content = content;
		this.autors = autors;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Item() {}
	@Override
	public String toString() {
		return ("Article : " + title + "\n(" + guid + ") Le = " + published );
	}
}
