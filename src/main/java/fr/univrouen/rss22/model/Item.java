package fr.univrouen.rss22.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
	private String date;
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Item(int guid, String title, String date) {
		super();
		this.guid = guid;
		this.title = title;
		this.date = date;
	}
	public Item() {}
	@Override
	public String toString() {
		return ("Article : " + title + "\n(" + guid + ") Le = " + date );
	}
}
