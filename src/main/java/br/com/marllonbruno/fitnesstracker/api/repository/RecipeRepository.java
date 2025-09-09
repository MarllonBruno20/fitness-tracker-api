package br.com.marllonbruno.fitnesstracker.api.repository;

import br.com.marllonbruno.fitnesstracker.api.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
