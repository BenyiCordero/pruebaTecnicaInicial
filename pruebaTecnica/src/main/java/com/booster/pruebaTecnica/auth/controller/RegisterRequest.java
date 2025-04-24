package com.booster.pruebaTecnica.auth.controller;

/**
 *
 * @param name
 * @param email
 * @param password
 */
public record RegisterRequest(
        String name,
        String email,
        String password
) {
}
