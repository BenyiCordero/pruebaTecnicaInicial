package com.booster.pruebaTecnica.auth.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @param accessToken
 * @param refreshToken
 */
public record TokenResponse(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken
) {
}
