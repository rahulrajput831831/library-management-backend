package com.lpu.LibraryManagementAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lpu.LibraryManagementAPI.Entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
