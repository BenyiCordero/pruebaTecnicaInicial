package com.booster.pruebaTecnica.auth.repository;

import com.booster.pruebaTecnica.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Token, @Entity, en el cual definimos nuestro id del token, token, tipo de token, si esta revoked,
 * o si esta expirado, al igual que un join con el user id
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TokenType tokenType = TokenType.BEARER;

    @Column(nullable = false)
    private Boolean isRevoked;

    @Column(nullable = false)
    private Boolean isExpired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public enum TokenType {
        BEARER
    }
}
