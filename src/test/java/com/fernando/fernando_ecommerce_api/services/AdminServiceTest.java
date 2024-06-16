package com.fernando.fernando_ecommerce_api.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExistsException;
import com.fernando.fernando_ecommerce_api.requests.CreateAdminRequest;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    
    private CreateAdminRequest createAdminRequest;

    @BeforeEach
    public void initAdmin() {
        createAdminRequest = new CreateAdminRequest("test", "test@test.com", "test123");
    }

    @Test
    public void shouldSaveAdminWithSuccess() {
        Assertions.assertDoesNotThrow(() -> adminService.saveAdmin(createAdminRequest));
    }

    @Test
    public void shouldSaveAdminWithError() {
        adminService.saveAdmin(createAdminRequest);
        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> adminService.saveAdmin(createAdminRequest));
    }
}