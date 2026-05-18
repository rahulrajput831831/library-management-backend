package com.lpu.LibraryManagementAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lpu.LibraryManagementAPI.Entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    // Used by login — find admin by email
    Optional<Admin> findByEmail(String email);
}
