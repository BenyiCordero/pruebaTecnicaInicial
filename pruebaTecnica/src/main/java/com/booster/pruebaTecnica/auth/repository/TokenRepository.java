package com.booster.pruebaTecnica.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository de token
 */
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
      select t from Token t
      where t.user.id = :id and (t.isExpired = false or t.isRevoked = false)
      """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);

}
