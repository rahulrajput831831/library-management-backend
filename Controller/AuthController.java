package com.lpu.LibraryManagementAPI.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.LibraryManagementAPI.Entity.Admin;
import com.lpu.LibraryManagementAPI.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * POST /auth/login
     * Body: { "email": "admin@library.com", "password": "admin" }
     * Returns admin info on success, 401 on failure.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email    = credentials.get("email");
        String password = credentials.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Email and password are required.");
        }

        Map<String, Object> result = authService.login(email, password);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password.");
        }

        return ResponseEntity.ok(result);
    }

    /**
     * POST /auth/register
     * Body: { "name": "...", "email": "...", "password": "..." }
     * Registers a new admin account.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin admin) {
        if (admin.getEmail() == null || admin.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required.");
        }
        Admin saved = authService.register(admin);
        if (saved == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("An admin with this email already exists.");
        }
        saved.setPassword(null); // don't return password in response
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * POST /auth/change-password
     * Body: { "email": "...", "oldPassword": "...", "newPassword": "..." }
     */
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> body) {
        boolean success = authService.changePassword(
            body.get("email"),
            body.get("oldPassword"),
            body.get("newPassword")
        );
        if (!success) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials or admin not found.");
        }
        return ResponseEntity.ok("Password changed successfully.");
    }
}
