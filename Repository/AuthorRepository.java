package com.lpu.LibraryManagementAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lpu.LibraryManagementAPI.Entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
