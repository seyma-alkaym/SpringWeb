package com.example.demo.service;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.exception.EntityNotFoundExceptionById;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepo;
import com.example.demo.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public List<Author> getAuthors(){
        return authorRepo.findAll();
    }

    public Author getAuthorById(Long authorId){
        return authorRepo.findById(authorId).orElseThrow();
    }

    public Author addAuthor(AuthorDTO authorDTO){
        Author author = Author.builder()
                    .name(authorDTO.getName())
                    .birthDate(authorDTO.getBirthDate())
                    .dateOfDeath(authorDTO.getDateOfDeath())
                    .email(authorDTO.getEmail())
                    .build();

        authorRepo.save(author);

        return author;
    }

    public Author updateAuthor(AuthorDTO authorDTO, Long authorId){
        if (!authorRepo.existsById(authorId)) {
            throw new EntityNotFoundExceptionById("Invalid Id Was Provided");
        } else {
            Author author = authorRepo.findById(authorId).get();

            author.setName(authorDTO.getName());
            author.setBirthDate(authorDTO.getBirthDate());
            author.setDateOfDeath(authorDTO.getDateOfDeath());
            author.setEmail(authorDTO.getEmail());

            authorRepo.save(author);

            return author;
        }
    }

    public ResponseEntity<?> deleteAuthor(Long authorId){
        if (!authorRepo.existsById(authorId)) {
            throw new EntityNotFoundExceptionById("Invalid Id was provided");
        } else {
            Author author = authorRepo.findById(authorId).get();
            Set<Book> books = bookRepo.findByAuthor(author).orElseThrow();

            if (books.size() != 0){
                MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
                headers.add("Delete-Massage", "The author cannot be deleted");

                return new ResponseEntity<>("",
                        new HttpHeaders(headers),
                        HttpStatus.BAD_REQUEST);
            }

            authorRepo.deleteById(authorId);

            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
            headers.add("Delete-Massage", "The author has been deleted successfully");

            return new ResponseEntity<>("",
                    new HttpHeaders(headers),
                    HttpStatus.NO_CONTENT);
        }
    }
}
