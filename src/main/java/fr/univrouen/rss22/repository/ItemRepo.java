package fr.univrouen.rss22.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.univrouen.rss22.model.Item;

public interface ItemRepo extends JpaRepository<Item, Long >{

	 @Query(value = "select * from item i where i.title like %:title%", nativeQuery = true)
	 List<Item> findByKeyword(@Param("title") String title);
	
}
