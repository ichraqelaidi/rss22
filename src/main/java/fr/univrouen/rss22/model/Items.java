package fr.univrouen.rss22.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "items")
@XmlAccessorType (XmlAccessType.FIELD)
public class Items 
{
  @XmlElement(name = "item")
  private List<Item> items = new ArrayList<Item>();
 
  public List<Item> getItems() {
    return items;
  }
 
  public void setItems(List<Item> items) {
    this.items = items;
  }
}