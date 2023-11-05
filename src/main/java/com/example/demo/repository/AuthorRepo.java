package com.example.demo.repository;

import com.example.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    boolean existsByName(String Name);


    @Query("SELECT a FROM Author a WHERE a.name = :name")
    Optional<Author> findByName(@Param("name") String name);

}
