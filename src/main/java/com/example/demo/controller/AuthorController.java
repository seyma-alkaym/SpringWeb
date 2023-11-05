package com.example.demo.controller;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("getAuthors")
    public ResponseEntity<?> getAuthors(){
        return ResponseEntity.ok(authorService.getAuthors());
    }

    @GetMapping("getAuthor/{authorId}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long authorId){
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @PostMapping("addAuthor")
    public ResponseEntity<?> addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = authorService.addAuthor(authorDTO);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Add-Massage", "The author has been added successfully.");

        return new ResponseEntity<>(author, new HttpHeaders(headers), HttpStatus.CREATED);
    }

    @PutMapping("updateAuthor/{authorId}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long authorId, @RequestBody AuthorDTO authorDTO){
        Author author = authorService.updateAuthor(authorDTO, authorId);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Update-Massage", "The author has been updated successfully.");

        return new ResponseEntity<>(author, new HttpHeaders(headers), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAuthor/{authorId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long authorId) {
        return authorService.deleteAuthor(authorId);
    }
}
