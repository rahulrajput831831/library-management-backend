package com.lpu.LibraryManagementAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lpu.LibraryManagementAPI.Entity.Book;
import com.lpu.LibraryManagementAPI.Service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // POST /books — Add a new book (pass authorId and categoryIds in the body)
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book saved = bookService.addBook(book);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // GET /books — Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // GET /books/{id} — Get a single book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    // PUT /books/{bookId}/borrow/{userId} — Borrow a book
    @PutMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<?> borrowBook(@PathVariable int bookId, @PathVariable int userId) {
        Book book = bookService.borrowBook(bookId, userId);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Book is already borrowed, or book/user not found.");
        }
        return ResponseEntity.ok(book);
    }

    // BONUS: PUT /books/{bookId}/return — Mark a book as returned
    @PutMapping("/{bookId}/return")
    public ResponseEntity<?> returnBook(@PathVariable int bookId) {
        Book book = bookService.returnBook(bookId);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book not found with id: " + bookId);
        }
        return ResponseEntity.ok(book);
    }

    // GET /books/user/{userId} — Get all books borrowed by a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Book>> getBooksByUser(@PathVariable int userId) {
        return ResponseEntity.ok(bookService.getBooksByUser(userId));
    }
}
