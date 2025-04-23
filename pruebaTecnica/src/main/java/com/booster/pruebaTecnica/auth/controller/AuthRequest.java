package com.booster.pruebaTecnica.auth.controller;

public record AuthRequest(
        String email,
        String password
) {
}
