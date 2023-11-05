package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("getBooks")
    public ResponseEntity<?> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("getBook/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable Long bookId){
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PostMapping("addBook")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO){
        Book book = bookService.addBook(bookDTO);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Add-Massage", "The book has been added successfully.");

        return new ResponseEntity<>(book, new HttpHeaders(headers), HttpStatus.CREATED);
    }

    @PutMapping("updateBook/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO){
        Book book = bookService.updateBook(bookDTO, bookId);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Update-Massage", "The book has been updated successfully.");

        return new ResponseEntity<>(book, new HttpHeaders(headers), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {

        return bookService.deleteBook(bookId);
    }
}
