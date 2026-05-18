package com.lpu.LibraryManagementAPI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.LibraryManagementAPI.Entity.Author;
import com.lpu.LibraryManagementAPI.Entity.Book;
import com.lpu.LibraryManagementAPI.Repository.AuthorRepository;
import com.lpu.LibraryManagementAPI.Repository.BookRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    // Fetch all books written by a specific author
    public List<Book> getBooksByAuthor(int authorId) {
        return bookRepository.findByAuthorAuthorId(authorId);
    }
}
