package com.booster.pruebaTecnica.auth.controller;

public record RegisterRequest(
        String name,
        String email,
        String password
) {
}
