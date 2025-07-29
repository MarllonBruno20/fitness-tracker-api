package br.com.marllonbruno.fitnesstracker.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "Por favor, informe o seu email")
        @Email(message = "Por favor, informe um email válido")
        String email,
        @NotBlank(message = "Por favor, informe a sua senha")
        String password
) {
}
