package com.lpu.LibraryManagementAPI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.LibraryManagementAPI.Entity.Author;
import com.lpu.LibraryManagementAPI.Entity.Book;
import com.lpu.LibraryManagementAPI.Entity.Category;
import com.lpu.LibraryManagementAPI.Entity.User;
import com.lpu.LibraryManagementAPI.Repository.AuthorRepository;
import com.lpu.LibraryManagementAPI.Repository.BookRepository;
import com.lpu.LibraryManagementAPI.Repository.CategoryRepository;
import com.lpu.LibraryManagementAPI.Repository.UserRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Add a new book.
     * Accepts author by authorId and categories by categoryId from the request body.
     * Looks up actual entities from the DB to establish proper FK references.
     */
    public Book addBook(Book book) {
        // Resolve Author from DB using the id passed in the request
        if (book.getAuthor() != null) {
            Author author = authorRepository.findById(book.getAuthor().getAuthorId()).orElse(null);
            book.setAuthor(author);
        }

        // Resolve each Category from DB
        if (book.getCategories() != null && !book.getCategories().isEmpty()) {
            List<Category> resolvedCategories = book.getCategories().stream()
                .map(c -> categoryRepository.findById(c.getCategoryId()).orElse(null))
                .filter(c -> c != null)
                .toList();
            book.setCategories(resolvedCategories);
        }

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    /**
     * Borrow a book — assigns it to a user.
     * Returns null if book is already borrowed or entities don't exist.
     */
    public Book borrowBook(int bookId, int userId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (book == null || user == null) {
            return null;
        }

        // Prevent borrowing a book that is already borrowed and not returned
        if (book.getBorrowedBy() != null && !book.isReturned()) {
            return null; // already borrowed
        }

        book.setBorrowedBy(user);
        book.setReturned(false);
        return bookRepository.save(book);
    }

    /**
     * BONUS: Mark a book as returned — clears the borrowedBy reference.
     */
    public Book returnBook(int bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            return null;
        }
        book.setBorrowedBy(null);
        book.setReturned(true);
        return bookRepository.save(book);
    }

    // Get all books borrowed by a user
    public List<Book> getBooksByUser(int userId) {
        return bookRepository.findByBorrowedByUserId(userId);
    }
}
