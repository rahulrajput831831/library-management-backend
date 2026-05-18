package com.lpu.LibraryManagementAPI.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lpu.LibraryManagementAPI.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Find all books borrowed by a specific user
    List<Book> findByBorrowedByUserId(int userId);

    // Find all books by a specific author
    List<Book> findByAuthorAuthorId(int authorId);
}
