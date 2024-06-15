package com.fernando.fernando_ecommerce_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.fernando.fernando_ecommerce_api.models.Client;


public interface ClientRepository extends JpaRepository<Client, Integer>{
    Optional<Client> findByEmail(String email);
    Optional<Client> findByCpf(String cpf);
}