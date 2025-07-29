package br.com.marllonbruno.fitnesstracker.api.controller;

import br.com.marllonbruno.fitnesstracker.api.dto.request.AuthenticationRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.request.UserRegisterRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.AuthenticationResponse;
import br.com.marllonbruno.fitnesstracker.api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint para registrar um novo usuário.
     * Após o registro bem-sucedido, retorna um token JWT para o usuário já ser autenticado.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        // A anotação @Valid ativa as validações que você colocou no DTO (ex: @NotBlank)
        AuthenticationResponse response = authService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para autenticar um usuário existente.
     * Retorna um token JWT em caso de sucesso.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {

        System.out.println("Entrou no login");

        return ResponseEntity.ok(authService.signIn(request));
    }

}
