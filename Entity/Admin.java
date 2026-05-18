package com.lpu.LibraryManagementAPI.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // plain-text for simplicity (no Spring Security needed)

    private String role = "admin";

    public Admin() {}

    // ─── Getters & Setters ───────────────────────────────────────────────────
    public int getAdminId()              { return adminId; }
    public void setAdminId(int adminId)  { this.adminId = adminId; }

    public String getName()              { return name; }
    public void setName(String name)     { this.name = name; }

    public String getEmail()             { return email; }
    public void setEmail(String email)   { this.email = email; }

    public String getPassword()                  { return password; }
    public void setPassword(String password)     { this.password = password; }

    public String getRole()              { return role; }
    public void setRole(String role)     { this.role = role; }
}
