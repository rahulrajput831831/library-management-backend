package com.lpu.LibraryManagementAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lpu.LibraryManagementAPI.Entity.Author;
import com.lpu.LibraryManagementAPI.Entity.Book;
import com.lpu.LibraryManagementAPI.Service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // POST /authors — Add a new author
    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author saved = authorService.addAuthor(author);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // GET /authors — Get all authors
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    // GET /authors/{id} — Get a single author by ID
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    // GET /authors/{id}/books — Fetch all books by this author
    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable int id) {
        return ResponseEntity.ok(authorService.getBooksByAuthor(id));
    }
}
