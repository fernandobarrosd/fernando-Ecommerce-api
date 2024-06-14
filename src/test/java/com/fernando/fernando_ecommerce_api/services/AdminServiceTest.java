package com.fernando.fernando_ecommerce_api.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExists;
import com.fernando.fernando_ecommerce_api.exceptions.EntityNotFoundException;
import com.fernando.fernando_ecommerce_api.models.Admin;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Admin admin;

    @BeforeEach
    public void initAdmin() {
        admin = new Admin(null, "test", "test@test.com", "test123");
    }

    @Test
    public void shouldSaveAdminWithSuccess() {
        Assertions.assertDoesNotThrow(() -> adminService.saveAdmin(admin));
    }

    @Test
    public void shouldSaveAdminWithError() {
        adminService.saveAdmin(admin);
        Assertions.assertThrows(EntityAlreadyExists.class, () -> adminService.saveAdmin(admin));
    }

    @Test
    public void shouldFindAdminWithSuccess() {
        adminService.saveAdmin(admin);
        Integer adminID = 1;
        Assertions.assertDoesNotThrow(() -> adminService.findById(adminID));
    }

    @Test
    public void shouldFindAdminWithError() {
        Integer adminID = 1;
        Assertions.assertThrows(EntityNotFoundException.class, (() -> adminService.findById(adminID)));
    }

    @Test
    public void shouldUpdatePassword() {
        adminService.saveAdmin(admin);
        String newPassword = "new_password123";

        adminService.updatePassword(1, newPassword);

        Admin adminWithID = adminService.findById(1);
        Assertions.assertTrue(passwordEncoder.matches(newPassword, adminWithID.getPassword()));
        
    }
}