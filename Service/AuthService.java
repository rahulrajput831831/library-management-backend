package com.lpu.LibraryManagementAPI.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.lpu.LibraryManagementAPI.Entity.Admin;
import com.lpu.LibraryManagementAPI.Repository.AdminRepository;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * Seed a default admin on first startup if none exists.
     * Credentials: admin@library.com / admin
     */
    @EventListener(ApplicationReadyEvent.class)
    public void seedDefaultAdmin() {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setName("Admin User");
            admin.setEmail("admin@library.com");
            admin.setPassword("admin");  // plain-text; swap BCrypt when adding Spring Security
            admin.setRole("admin");
            adminRepository.save(admin);
            System.out.println("✅ Default admin seeded: admin@library.com / admin");
        }
    }

    /**
     * Login — checks email + password, returns user info map on success.
     * Returns null on failure.
     */
    public Map<String, Object> login(String email, String password) {
        Admin admin = adminRepository.findByEmail(email).orElse(null);

        if (admin == null || !admin.getPassword().equals(password)) {
            return null; // invalid credentials
        }

        // Build response payload (no JWT needed — simple session approach)
        Map<String, Object> response = new HashMap<>();
        response.put("adminId", admin.getAdminId());
        response.put("name",    admin.getName());
        response.put("email",   admin.getEmail());
        response.put("role",    admin.getRole());
        response.put("message", "Login successful");
        return response;
    }

    // Register a new admin (optional — useful for setup)
    public Admin register(Admin admin) {
        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            return null; // email already exists
        }
        return adminRepository.save(admin);
    }

    // Change password
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin == null || !admin.getPassword().equals(oldPassword)) {
            return false;
        }
        admin.setPassword(newPassword);
        adminRepository.save(admin);
        return true;
    }
}
