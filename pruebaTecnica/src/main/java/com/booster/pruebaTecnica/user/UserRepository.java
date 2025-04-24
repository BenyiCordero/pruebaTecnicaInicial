package com.booster.pruebaTecnica.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository de nuestro usuario
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

}
