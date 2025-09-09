package br.com.marllonbruno.fitnesstracker.api.repository;

import br.com.marllonbruno.fitnesstracker.api.dto.request.IngredientCreationRequest;
import br.com.marllonbruno.fitnesstracker.api.dto.response.IngredientCreationResponse;
import br.com.marllonbruno.fitnesstracker.api.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByDisplayName(String displayName);

    List<Ingredient> findByDisplayNameContainingIgnoreCaseOrSearchTagsContainingIgnoreCase(String searchTerm, String searchTermAgain);

    @Query("SELECT i FROM Ingredient i WHERE lower(i.displayName) LIKE lower(concat('%', :term, '%')) OR lower(i.searchTags) LIKE lower(concat('%', :term, '%'))")
    List<Ingredient> searchIngredients(@Param("term") String searchTerm);
}
