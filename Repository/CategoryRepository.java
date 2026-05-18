package com.lpu.LibraryManagementAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lpu.LibraryManagementAPI.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
