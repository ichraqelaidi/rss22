package fr.univrouen.rss22.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.univrouen.rss22.model.Feed;
import fr.univrouen.rss22.model.Item;

public interface FeedRepo extends JpaRepository<Feed, Long >{
	

}
