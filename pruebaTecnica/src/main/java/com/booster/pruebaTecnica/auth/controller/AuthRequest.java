package com.booster.pruebaTecnica.auth.controller;

/**
 *
 * @param email
 * @param password
 */
public record AuthRequest(
        String email,
        String password
) {
}
