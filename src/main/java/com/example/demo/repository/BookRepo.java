package com.example.demo.repository;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface BookRepo extends JpaRepository<Book, Long> {
    Optional<Set<Book>> findByAuthor(Author author);
}
