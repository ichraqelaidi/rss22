package fr.univrouen.rss22.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univrouen.rss22.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Long >{

}
