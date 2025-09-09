package br.com.marllonbruno.fitnesstracker.api.service;

import br.com.marllonbruno.fitnesstracker.api.entity.User;
import br.com.marllonbruno.fitnesstracker.api.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AutheticatedUserService {

    private final UserRepository userRepository;

    public AutheticatedUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Busca o usuário autenticado no contexto de segurança e retorna a entidade completa do banco de dados.
     * @return A entidade User do usuário atualmente logado.
     * @throws RuntimeException se nenhum usuário estiver autenticado ou não for encontrado no banco.
     */
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new IllegalStateException("Nenhum usuário autenticado encontrado. Acesso negado.");
        }

        String userEmail = authentication.getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário autenticado não encontrado no banco de dados: " + userEmail));
    }

}
