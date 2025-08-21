package br.com.marllonbruno.fitnesstracker.api.repository;

import br.com.marllonbruno.fitnesstracker.api.dto.request.IngredientCreationRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.IngredientCreationResponse;
import br.com.marllonbruno.fitnesstracker.api.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByDisplayName(String displayName);
}
