package br.gov.mt.seplag.servidores.repository;

import  br.gov.mt.seplag.servidores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
