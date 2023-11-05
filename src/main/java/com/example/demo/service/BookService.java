package com.example.demo.service;

import com.example.demo.dto.BookDTO;
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

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public ResponseEntity<?> getBooks(){
        return ResponseEntity.ok(bookRepo.findAll());
    }

    public Book getBookById(Long bookId){
        return bookRepo.findById(bookId).orElseThrow();
    }

    public Book addBook(BookDTO bookDTO){
        Author author;

        if (!authorRepo.existsByName(bookDTO.getAuthorName())) {
            author = Author.builder()
                    .name(bookDTO.getAuthorName())
                    .build();
        } else {
            author = authorRepo.findByName(bookDTO.getAuthorName()).get();
        }

        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .publishDate(bookDTO.getPublishDate())
                .summary(bookDTO.getSummary())
                .language(bookDTO.getLanguage())
                .author(author)
                .build();

        bookRepo.save(book);

        return book;
    }

    public Book updateBook(BookDTO bookDTO, Long bookId) {
        if (!bookRepo.existsById(bookId)) {
            throw new EntityNotFoundExceptionById("Invalid Id was provided");
        } else {
            Book book = bookRepo.findById(bookId).get();

            book.setTitle(bookDTO.getTitle());
            book.setSummary(bookDTO.getSummary());
            book.setPublishDate(bookDTO.getPublishDate());
            book.setLanguage(bookDTO.getLanguage());

            Author author;

            if (!authorRepo.existsByName(bookDTO.getAuthorName())) {
                author = Author.builder()
                        .name(bookDTO.getAuthorName())
                        .build();
            } else {
                author = authorRepo.findByName(bookDTO.getAuthorName()).get();
            }

            book.setAuthor(author);

            bookRepo.save(book);

            return book;
        }
    }

    public ResponseEntity<?> deleteBook(Long bookId){
        if (!bookRepo.existsById(bookId)) {
            throw new EntityNotFoundExceptionById("Invalid Id was provided");
        } else {
            bookRepo.deleteById(bookId);

            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
            headers.add("Delete-Massage", "The book has been deleted successfully");

            return new ResponseEntity<>("",
                    new HttpHeaders(headers),
                    HttpStatus.NO_CONTENT);
        }
    }
}
