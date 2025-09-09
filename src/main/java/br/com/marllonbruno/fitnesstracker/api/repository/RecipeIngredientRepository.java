package br.com.marllonbruno.fitnesstracker.api.repository;

import br.com.marllonbruno.fitnesstracker.api.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
}
