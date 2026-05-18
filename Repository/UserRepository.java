package com.lpu.LibraryManagementAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lpu.LibraryManagementAPI.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
