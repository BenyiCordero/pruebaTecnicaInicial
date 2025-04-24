package com.booster.pruebaTecnica.user;

import jakarta.persistence.*;
import com.booster.pruebaTecnica.auth.repository.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * Entidad que representa a un usuario en la base de datos.
 * <p>
 * Cada usuario tiene un nombre, un correo electrónico único, una contraseña cifrada
 * y una lista de tokens asociados que se utilizan para autenticación mediante JWT.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;


}
