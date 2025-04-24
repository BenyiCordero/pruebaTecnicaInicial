package com.booster.pruebaTecnica.auth.controller;

import com.booster.pruebaTecnica.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * RestController en el cual mapeamos las rutas para poder acceder a la /auth de register, login y
 * refresh token, aqui llamamos a nuestro AuthService
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    /**
     *
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest request) {
        final TokenResponse response = service.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest request) {
        final TokenResponse response = service.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication
    ) {
        return service.refreshToken(authentication);
    }

}
