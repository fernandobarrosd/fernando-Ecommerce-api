package com.fernando.fernando_ecommerce_api.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fernando.fernando_ecommerce_api.models.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);

    @Query("UPDATE Admin admin SET admin.password = :newPassword WHERE admin.id = :id")
    void updatePassword(
        @Param("id")
        Integer id, 

        @Param("newPassword")
        String newPassword);
}