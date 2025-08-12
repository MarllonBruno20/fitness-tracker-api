package br.com.marllonbruno.fitnesstracker.api.service;

import br.com.marllonbruno.fitnesstracker.api.dto.request.AuthenticationRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.request.UserRegisterRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.AuthenticationResponse;
import br.com.marllonbruno.fitnesstracker.api.entity.User;
import br.com.marllonbruno.fitnesstracker.api.exception.EmailAlreadyExistsException;
import br.com.marllonbruno.fitnesstracker.api.repository.UserRepository;
import br.com.marllonbruno.fitnesstracker.api.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse signUp(UserRegisterRequest userRegisterRequest) {

        // Usando nossa exceção customizada
        if (userRepository.findByEmail(userRegisterRequest.email()).isPresent()) {
            throw new EmailAlreadyExistsException("O email informado já está em uso.");
        }

        User user = new User();
        user.setName(userRegisterRequest.name());
        user.setEmail(userRegisterRequest.email());
        user.setPasswordHash(passwordEncoder.encode(userRegisterRequest.password()));

        userRepository.save(user);

        String jwtToken = jwtUtil.generateToken(user);
        return new AuthenticationResponse(jwtToken, false);
    }

    // Dentro da classe AuthService.java

    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest) {

        try {
            System.out.println("Tentando autenticar o usuário: " + authenticationRequest.email());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.email(),
                            authenticationRequest.password()
                    )
            );
        } catch (Exception e) {
            // ESTA É A PARTE IMPORTANTE
            System.err.println("!!! OCORREU UM ERRO DURANTE A AUTENTICAÇÃO !!!");
            System.err.println("Tipo da Exceção: " + e.getClass().getName());
            System.err.println("Mensagem da Exceção: " + e.getMessage());
            e.printStackTrace(); // Imprime o stack trace completo no console de erro
            // Lança a exceção novamente para manter o fluxo de erro, se necessário, ou retorne um erro específico
            throw e;
        }

        System.out.println("Autenticação bem-sucedida para: " + authenticationRequest.email());

        var user = userRepository
                .findByEmail(authenticationRequest.email())
                .orElseThrow(() -> new RuntimeException("Erro inesperado ao buscar usuário após autenticação."));

        String jwtToken = jwtUtil.generateToken(user);
        boolean isProfileComplete = user.isProfileComplete();

        return new AuthenticationResponse(jwtToken, isProfileComplete);
    }

}
