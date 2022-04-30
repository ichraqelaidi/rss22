package fr.univrouen.rss22.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "feed")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "feed")
public class Feed {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@XmlElement
	private String title;
	@XmlElement
	private String pubDate;
	@XmlElement
	private String copyright;
	@XmlElement
	private String link;
	@XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Item> items;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public Feed(long id, String title, String pubDate, String copyright, String link, Set<Item> items) {
		super();
		this.id = id;
		this.title = title;
		this.pubDate = pubDate;
		this.copyright = copyright;
		this.link = link;
		this.items = items;
	}
	public Feed() {
	}
	

}
