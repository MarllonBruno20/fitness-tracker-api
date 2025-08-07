package br.com.marllonbruno.fitnesstracker.api.controller;

import br.com.marllonbruno.fitnesstracker.api.dto.request.ProfileUpdateRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.ProfileDataResponse;
import br.com.marllonbruno.fitnesstracker.api.entity.User;
import br.com.marllonbruno.fitnesstracker.api.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * Endpoint para buscar as informações de perfil do usuário autenticado.
     */
    @GetMapping
    public ResponseEntity<ProfileDataResponse> getProfile() {
        // O serviço busca o usuário autenticado do contexto de segurança
        ProfileDataResponse profileData = profileService.getProfileData();

        // Converte a entidade User para o DTO de resposta e retorna
        return ResponseEntity.ok(profileData);
    }

    /**
     * Endpoint para atualizar as informações de perfil do usuário autenticado.
     */
    @PutMapping
    public ResponseEntity<ProfileDataResponse> updateProfile(
            @Valid @RequestBody ProfileUpdateRequest request
    ) {
        // O serviço atualiza o usuário e executa os cálculos
        User updatedUser = profileService.updateUser(request);

        // Converte a entidade User atualizada para o DTO de resposta e retorna
        return ResponseEntity.ok(new ProfileDataResponse(updatedUser));
    }
}
