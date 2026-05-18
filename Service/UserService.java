package com.lpu.LibraryManagementAPI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.LibraryManagementAPI.Entity.Profile;
import com.lpu.LibraryManagementAPI.Entity.User;
import com.lpu.LibraryManagementAPI.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create user — profile is auto-created via CascadeType.ALL
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // BONUS: Update user and profile fields
    public User updateUser(int id, User updatedUser) {
        User existing = userRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setUserName(updatedUser.getUserName());

        // Update profile fields if provided
        if (updatedUser.getProfile() != null && existing.getProfile() != null) {
            Profile existingProfile = existing.getProfile();
            Profile updatedProfile = updatedUser.getProfile();

            if (updatedProfile.getEmail() != null) {
                existingProfile.setEmail(updatedProfile.getEmail());
            }
            if (updatedProfile.getPhone() != null) {
                existingProfile.setPhone(updatedProfile.getPhone());
            }
            if (updatedProfile.getAddress() != null) {
                existingProfile.setAddress(updatedProfile.getAddress());
            }
        }
        return userRepository.save(existing);
    }

    // BONUS: Delete user (also deletes profile via CascadeType.ALL)
    public String deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            return "User with id " + id + " not found.";
        }
        userRepository.deleteById(id);
        return "User with id " + id + " deleted successfully.";
    }
}
