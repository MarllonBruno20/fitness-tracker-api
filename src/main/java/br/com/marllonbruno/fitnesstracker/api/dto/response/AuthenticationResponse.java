package br.com.marllonbruno.fitnesstracker.api.dto.response;

public record AuthenticationResponse(
        String token,
        Boolean isProfileComplete
) {
}
